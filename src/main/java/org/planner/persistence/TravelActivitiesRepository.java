package org.planner.persistence;

import org.planner.domain.Activity;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TravelActivitiesRepository extends Repository {

    public TravelActivitiesRepository() {
        super(TravelActivitiesRepository.class.getSimpleName(), "travel_id,activity_id");
    }

    public void persistTravelActivityMap(Map<Long, List<Long>> map) {
        Map<Long, List<Long>> mapToSave = filterExistingTravelActivitiesOut(map);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (Long travelId : mapToSave.keySet()) {
                for (Long activityId : mapToSave.get(travelId)) {
                    bw.write("\n" + travelId + "," + activityId);
                }
            }
        } catch (IOException e) {
            logger.info("Activity list could not be persisted.");
        }
        logger.info("Activity list persisted successfully.");
    }

    public Map<Long, List<Long>> loadTravelActivityMap() {
        Map<Long, List<Long>> map = new ConcurrentHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            br.readLine(); // skip the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Long travelId = Long.parseLong(data[0]);
                Long activityId = Long.parseLong(data[1]);

                if (!map.containsKey(travelId)) {
                    map.put(travelId, new CopyOnWriteArrayList<>());
                }
                map.get(travelId).add(activityId);
            }
            logger.info("Activity list loaded successfully.");
        } catch (IOException e) {
            logger.info("Activity list could not be loaded.");
        }
        return map;
    }

    public Map<Long, List<Long>> filterExistingTravelActivitiesOut(Map<Long, List<Long>> map) {
        Map<Long, List<Long>> mapToSave = map;
        for (Long travelId : mapToSave.keySet()) {
            for (Long activityId : mapToSave.get(travelId)) {
                if (existsInFile(travelId, activityId)) {
                    mapToSave.get(travelId).remove(activityId);
                }
            }
        }
        logger.info("Activity list filtered successfully.");
        return mapToSave;
    }

    public boolean existsInFile(Long travelId, Long activityId) {
        Map<Long, List<Long>> map = loadTravelActivityMap();
        for (Long traId : map.keySet()) {
            for (Long actId : map.get(traId)) {
                if (traId == travelId && actId == activityId) {
                    logger.info("Activity already exists in file.");
                    return true;
                }
            }
        }
        return false;
    }

    public void removeTravelActivitiesByTravelId(Long travelId) {
        Map<Long, List<Long>> map = loadTravelActivityMap();
        for (Long traId : map.keySet()) {
            if (traId == travelId) {
                map.remove(traId);
                break;
            }
        }
        deleteFile();
        persistTravelActivityMap(map);
        logger.info("Entries with travel id: " + travelId + " removed successfully.");
    }

    public void removeTravelActivity(Long travelId, Long activityId) {
        Map<Long, List<Long>> map = loadTravelActivityMap();
        for (Long traId : map.keySet()) {
            for (Long actId : map.get(traId)) {
                if (travelId == traId && activityId == actId) {
                    map.get(traId).remove(actId);
                    break;
                }
            }
        }
        deleteFile();
        persistTravelActivityMap(map);
        logger.info("Entry with travel id: " + travelId + " and activity id: " + activityId + " removed successfully.");
    }

    public List<Activity> getTravelActivitiesByTravel(Long travelId) {
        List<Activity> activityList = new CopyOnWriteArrayList<>();
        Map<Long, List<Long>> map = loadTravelActivityMap();
        for (Long traId : map.keySet()) {
            if (traId == travelId) {
                for (Long actId : map.get(traId)) {
                    activityList.add(ActivityRepository.getActivityById(actId));
                }
            }
        }
        return activityList;
    }
}
