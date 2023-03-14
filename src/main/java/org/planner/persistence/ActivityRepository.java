package org.planner.persistence;

import org.planner.Debug;
import org.planner.domain.Activity;

import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ActivityRepository extends Repository {

    protected static final String filePath = Repository.ROOT_PATH + ActivityRepository.class.getSimpleName() + Repository.FILE_EXTENSION;

    public ActivityRepository() {
        super(ActivityRepository.class.getSimpleName(), Activity.getColumns());
    }

    public static List<Activity> loadList() {
        List<Activity> activities = new CopyOnWriteArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // skip the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Activity activity = new Activity(Long.parseLong(data[0]), data[1], data[2], data[3], data[4], Double.parseDouble(data[5]), Long.parseLong(data[6]), data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14], Double.parseDouble(data[15]), Double.parseDouble(data[16]), data[17], data[18], data[19]);
                activities.add(activity);
            }
            logger.info("Activity list loaded successfully.");
        } catch (IOException e) {
            logger.info("Activity list could not be loaded.");
        }
        return activities;
    }

    public static long getNewId() {
        List<Activity> activities = loadList();
        Long activityId = 0L;
        for (Activity activity : activities) {
            Long id = activity.getId();
            if (id > activityId) {
                activityId = id;
            }
        }
        return activityId + 1;
    }

    public static boolean existsInFile(Activity activity) {
        List<Activity> activities = loadList();
        for (Activity a : activities) {
            if (a.getInternalId().equals(activity.getInternalId())) {
                logger.info("Activity already exists in file.");
                return true;
            }
        }
        return false;
    }

    public static Activity getActivityById(Long actId) {
        List<Activity> activities = loadList();
        for (Activity activity : activities) {
            if (activity.getId() == actId) {
                return activity;
            }
        }
        return null;
    }

    public static Activity getActivityByInternalId(String internalId) {
        List<Activity> activities = loadList();
        for (Activity activity : activities) {
            if (activity.getInternalId().equals(internalId)) {
                return activity;
            }
        }
        return null;
    }

    public void persistList(List<Activity> activities) {
        List<Activity> activitiesToSave = filterExisting(activities);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Activity activity : activitiesToSave) {
                bw.write("\n" + activity.toCsv());
            }
        } catch (IOException e) {
            logger.info("Activity list could not be persisted.");
        }
        logger.info("Activity list persisted successfully.");

    }

    public List<Activity> filterExisting(List<Activity> activities) {
        List<Activity> activitiesToSave = new CopyOnWriteArrayList<>();
        for (Activity activity : activities) {
            if (!existsInFile(activity)) {
                activitiesToSave.add(activity);
            }
        }
        logger.info("Activity list filtered successfully.");
        return activitiesToSave;
    }

    public void removeEntryByActivityId(Long activityId) {
        List<Activity> activities = loadList();
        for (Activity activity : activities) {
            if (activity.getId() == activityId) {
                activities.remove(activity);
                break;
            }
        }
        deleteFile();
        persistList(activities);
        logger.info("Activity removed successfully.");
    }

    public static List<Activity> loadListByCity(String city) {
        List<Activity> activities = loadList();
        for (Activity activity : activities) {
            if (!activity.getCity().equals(city)) {
                activities.add(activity);
            }
        }
        logger.info("Activity list loaded successfully.");
        return activities;
    }
}
