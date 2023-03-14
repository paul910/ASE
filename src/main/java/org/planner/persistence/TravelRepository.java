package org.planner.persistence;

import org.planner.domain.Travel;
import org.planner.domain.User;

import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TravelRepository extends Repository {

    protected static String filePath = Repository.ROOT_PATH + TravelRepository.class.getSimpleName() + Repository.FILE_EXTENSION;

    public TravelRepository() {
        super(TravelRepository.class.getSimpleName(), Travel.getColumns());
    }

    public static Long getNewId() {
        List<Travel> travels = loadTravelList();
        Long travelId = 0L;
        for (Travel travel : travels) {
            Long id = travel.getId();
            if (id > travelId) {
                travelId = id;
            }
        }
        return travelId + 1;
    }

    protected static List<Travel> loadTravelList() {
        List<Travel> travels = new CopyOnWriteArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // skip the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Travel travel = new Travel(Long.parseLong(data[0]), data[1], data[2], Double.parseDouble(data[3]), Travel.DATE_FORMAT.parse(data[4]), Travel.DATE_FORMAT.parse(data[5]), Travel.DATE_TIME_FORMAT.parse(data[6]), Travel.DATE_TIME_FORMAT.parse(data[7]));
                travels.add(travel);
            }
            logger.info("Travel list loaded successfully.");
        } catch (IOException | ParseException e) {
            logger.info("Travel list could not be loaded.");
        }
        return travels;
    }

    public void persistTravelList(List<Travel> list) {
        List<Travel> travelsToSave = filterExistingTravelOut(list);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Travel travel : travelsToSave) {
                bw.write("\n" + travel.toCsv());
            }
        } catch (IOException e) {
            logger.info("Travel list could not be persisted.");
        }
        logger.info("Travel list persisted successfully.");
    }

    public List<Travel> loadTravelListByUser(User user) {
        List<Travel> travels = loadTravelList();
        for (Travel travel : travels) {
            if (!travel.getCreatedBy().equals(user.getUsername())) {
                travels.remove(travel);
            }
        }
        logger.info("Travel list loaded successfully for user: " + user.getUsername() + ".");
        return travels;
    }

    public List<Travel> filterExistingTravelOut(List<Travel> travels) {
        List<Travel> travelsToSave = new CopyOnWriteArrayList<>();
        for (Travel travel : travels) {
            if (!existsInFile(travel)) {
                travelsToSave.add(travel);
            }
        }
        logger.info("Travel list filtered successfully.");
        return travelsToSave;
    }

    public boolean existsInFile(Travel travel) {
        List<Travel> travels = loadTravelList();
        for (Travel tra : travels) {
            if (tra.getId() == travel.getId()) {
                logger.info("Travel already exists in file.");
                return true;
            }
        }
        return false;
    }

    public void removeTravelByTravelId(Long travelId) {
        List<Travel> travels = loadTravelList();
        for (Travel travel : travels) {
            if (travel.getId() == travelId) {
                travels.remove(travel);
                // Consistency: All Activities of the Travel must also be deleted
                new TravelActivitiesRepository().removeTravelActivitiesByTravelId(travelId);
                break;
            }
        }
        deleteFile();
        persistTravelList(travels);
        logger.info("Travel removed successfully.");
    }

    public void removeTravelByUser(User user) {
        List<Travel> travels = loadTravelList();
        for (Travel travel : travels) {
            if (travel.getCreatedBy().equals(user.getUsername())) {
                travels.remove(travel);
                // Consistency: All Activities of the Travel must also be deleted
                new TravelActivitiesRepository().removeTravelActivitiesByTravelId(travel.getId());
                break;
            }
        }
        deleteFile();
        persistTravelList(travels);
        logger.info("Travel removed successfully.");
    }
}
