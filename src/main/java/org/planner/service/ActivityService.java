package org.planner.service;

import org.planner.api.ActivityAPI;
import org.planner.domain.Activity;
import org.planner.helper.JsonParser;
import org.planner.persistence.ActivityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ActivityService {
    private final Logger logger;
    private final ActivityRepository activityRepository;
    private final List<Activity> activities;
    private final ActivityAPI activityAPI;

    public ActivityService() {
        this.logger = Logger.getLogger(ActivityService.class.getName());
        this.activityRepository = new ActivityRepository();
        this.activities = new ArrayList<>();
        this.activityAPI = new ActivityAPI();
    }

    //PERISTENCE

    public void persistActivity(Activity activity) {
        List<Activity> activities = new ArrayList<>();
        activities.add(activity);
        this.activityRepository.persistList(activities);
    }

    public void loadActivities() {
        this.activities.clear();
        this.activities.addAll(this.activityRepository.loadList());
    }

    public void loadActivitiesByCity(String city) {
        this.activities.clear();
        this.activities.addAll(this.activityRepository.loadListByCity(city));
    }

    //API
    public void fetchActivitiesByCity(String city) {
        String activityString = this.activityAPI.request(city);
        if (activityString == null) {
            return;
        }

        this.activities.clear();
        // TODO: replace all the unicode characters with their actual characters
        // activityString = activityString.replaceAll("\\\\u0026", "&"); etc.
        Map<String, Object> obj = new JsonParser(activityString).parseObject();
        List<Map<String, Object>> businesses = (List<Map<String, Object>>) obj.get("businesses");

        for (Map<String, Object> activityMap : businesses) {
            Activity activity = new Activity(activityMap);
            if (ActivityRepository.existsInFile(activity)) {
                activity = ActivityRepository.getActivityByInternalId(activity.getInternalId());
            }
            this.persistActivity(activity);
            this.activities.add(activity);
        }
        logger.info("Activities fetched successfully.");
    }

    //GETTER

    public List<Activity> getActivities() {
        return this.activities;
    }

    public Activity getActivityById(String id) {
        for (Activity activity : this.activities) {
            if (activity.getId().toString().equals(id)) {
                return activity;
            }
        }
        return null;
    }

    public void setActivities(List<Activity> activityList) {
        this.activities.clear();
        this.activities.addAll(activityList);
    }
}
