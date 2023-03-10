package org.planner.persistence;

import org.planner.domain.Activity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ActivityRepository {
    private final Logger logger;
    private final File file;
    private final String filePath;

    public ActivityRepository(String rootPath) {
        this.logger = Logger.getLogger(ActivityRepository.class.getName());
        this.filePath = rootPath + "\\activities.csv";
        this.file = new File(this.filePath);

        File root = new File(rootPath);
        if (!root.exists()) root.mkdir();
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

    public void persistActivityList(List<Activity> activities) {
        // Remove activities that already exist in the file
        List<Activity> activitiesToSave = new ArrayList<>();
        for (Activity activity : activities) {
            if (!activityExistsInFile(activity)) {
                activitiesToSave.add(activity);
            }
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath, true));

            // Write each activity to the CSV file
            for (Activity activity : activitiesToSave) {
                writer.write("\n");
                writer.write(activity.getId() + ",");
                writer.write(activity.getAlias() + ",");
                writer.write(activity.getName() + ",");
                writer.write(activity.getImage_url() + ",");
                writer.write(activity.getRating() + ",");
                writer.write(activity.getReviewCount() + ",");
                writer.write(activity.getPrice() + ",");
                writer.write(activity.getCountry() + ",");
                writer.write(activity.getState() + ",");
                writer.write(activity.getCity() + ",");
                writer.write(activity.getAddress1() + ",");
                writer.write(activity.getAddress2() + ",");
                writer.write(activity.getAddress3() + ",");
                writer.write(activity.getZipCode() + ",");
                writer.write(activity.getLatitude() + ",");
                writer.write(activity.getLongitude() + ",");
                writer.write(activity.getUrl() + ",");
                writer.write(activity.getPhone() + ",");
                writer.write(activity.getDisplay_phone());
            }
            writer.close();
            this.logger.info("Activity list persisted successfully.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Activity> loadActivityList() {
        List<Activity> activities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            br.readLine(); // skip the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Activity activity = new Activity(data[0], data[1], data[2], data[3], Double.parseDouble(data[4]), Long.parseLong(data[5]), data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], Double.parseDouble(data[14]), Double.parseDouble(data[15]), data[16], data[17], data[18]);

                activities.add(activity);
            }
            logger.info("Activity list loaded successfully.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return activities;
    }

    public void deleteActivityList() {
        this.file.delete();
    }

    public boolean activityExistsInFile(Activity activity) {
        List<Activity> activities = loadActivityList();
        for (Activity a : activities) {
            if (a.getId().equals(activity.getId())) {
                logger.info("Activity already exists in file.");
                return true;
            }
        }
        return false;
    }
}
