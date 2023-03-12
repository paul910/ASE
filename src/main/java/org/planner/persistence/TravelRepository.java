package org.planner.persistence;

import org.planner.domain.Travel;
import org.planner.domain.User;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TravelRepository implements RepositoryInterface<Travel> {
    private final String filePath;
    private final File file;

    public TravelRepository() {
        this.filePath = ROOT_PATH + "\\" + Travel.class.getName() + "" + FILE_EXTENSION;
        this.file = new File(this.filePath);

        createFolderIfNotExists();
        createFileIfNotExists();
    }

    public static Long getNewId() {
        List<Travel> travels = new TravelRepository().loadList();
        Long travelId = 0L;
        for (Travel travel : travels) {
            Long id = travel.getId();
            if (id > travelId) {
                travelId = id;
            }
        }
        return travelId + 1;
    }

    private void createFolderIfNotExists() {
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdir();
        }
    }

    private void createFileIfNotExists() {
        if (!this.file.exists()) {
            try {
                file.createNewFile();
                FileWriter writer = new FileWriter(filePath);
                writer.append(Travel.getColumns());
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void persistList(List<Travel> list) {
        List<Travel> travelsToSave = filterExisting(list);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Travel travel : travelsToSave) {
                bw.write("\n" + travel.toString());
            }
        } catch (IOException e) {
            this.LOGGER.info("Travel list could not be persisted.");
        }
        this.LOGGER.info("Travel list persisted successfully.");
    }

    @Override
    public List<Travel> loadList() {
        List<Travel> travels = new CopyOnWriteArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            br.readLine(); // skip the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Travel travel = new Travel(Long.parseLong(data[0]), data[1], data[2], Double.parseDouble(data[3]), Travel.DATE_FORMAT.parse(data[4]), Travel.DATE_FORMAT.parse(data[5]), Travel.DATE_TIME_FORMAT.parse(data[6]), Travel.DATE_TIME_FORMAT.parse(data[7]));
                travels.add(travel);
            }
            this.LOGGER.info("Travel list loaded successfully.");
        } catch (IOException | ParseException e) {
            this.LOGGER.info("Travel list could not be loaded.");
        }
        return travels;
    }

    public List<Travel> loadListForUser(User user) {
        List<Travel> travels = loadList();
        for (Travel travel : travels) {
            if (!travel.getCreatedBy().equals(user.getUsername())) {
                travels.remove(travel);
            }
        }
        LOGGER.info("Travel list loaded successfully for user: " + user.getUsername() + ".");
        return travels;
    }

    @Override
    public void deleteList() {
        this.file.delete();
        createFileIfNotExists();
        this.LOGGER.info("Travel list deleted successfully.");
    }

    @Override
    public List<Travel> filterExisting(List<Travel> travels) {
        List<Travel> travelsToSave = new ArrayList<>();
        for (Travel travel : travels) {
            if (!existsInFile(travel)) {
                travelsToSave.add(travel);
            }
        }
        LOGGER.info("Travel list filtered successfully.");
        return travelsToSave;
    }

    @Override
    public boolean existsInFile(Travel object) {
        List<Travel> travels = loadList();
        for (Travel travel : travels) {
            if (travel.getId() == object.getId()) {
                LOGGER.info("Travel already exists in file.");
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeEntryById(String id) {
        List<Travel> travels = loadList();
        for (Travel travel : travels) {
            if (travel.getId() == Long.parseLong(id)) {
                travels.remove(travel);
                break;
            }
        }
        deleteList();
        persistList(travels);
        LOGGER.info("Travel removed successfully.");
    }
}
