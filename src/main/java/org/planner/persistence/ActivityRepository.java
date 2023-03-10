package org.planner.persistence;

import org.planner.domain.Activity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityRepository implements RepositoryInterface<Activity> {
    private final String filePath;
    private final File file;

    public ActivityRepository() {
        this.filePath = ROOT_PATH + "\\" + Activity.class.getName() + "" + FILE_EXTENSION;
        this.file = new File(this.filePath);

        createFolderIfNotExists();
        createFileIfNotExists();
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
                writer.append(Activity.getColumns());
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void persistList(List<Activity> activities) {
        List<Activity> activitiesToSave = filterExisting(activities);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Activity activity : activitiesToSave) {
                bw.write("\n" + activity.toString());
            }
        } catch (IOException e) {
            this.LOGGER.info("Activity list could not be persisted.");
        }
        this.LOGGER.info("Activity list persisted successfully.");

    }

    @Override
    public List<Activity> loadList() {
        List<Activity> activities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            br.readLine(); // skip the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Activity activity = new Activity(data[0], data[1], data[2], data[3], Double.parseDouble(data[4]), Long.parseLong(data[5]), data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], Double.parseDouble(data[14]), Double.parseDouble(data[15]), data[16], data[17], data[18]);
                activities.add(activity);
            }
            LOGGER.info("Activity list loaded successfully.");
        } catch (IOException e) {
            LOGGER.info("Activity list could not be loaded.");
        }
        return activities;
    }

    @Override
    public void deleteList() {
        this.file.delete();
        LOGGER.info("Activity list deleted successfully.");
    }

    @Override
    public List<Activity> filterExisting(List<Activity> activities) {
        List<Activity> activitiesToSave = new ArrayList<>();
        for (Activity activity : activities) {
            if (!existsInFile(activity)) {
                activitiesToSave.add(activity);
            }
        }
        LOGGER.info("Activity list filtered successfully.");
        return activitiesToSave;
    }

    @Override
    public boolean existsInFile(Activity activity) {
        List<Activity> activities = loadList();
        for (Activity a : activities) {
            if (a.getId().equals(activity.getId())) {
                LOGGER.info("Activity already exists in file.");
                return true;
            }
        }
        return false;
    }
}
