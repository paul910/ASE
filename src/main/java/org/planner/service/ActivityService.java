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
    public void loadActivities() {
        this.activities.clear();
        this.activities.addAll(this.activityRepository.loadList());
    }

    public void persistActivities() {
        this.activityRepository.persistList(this.activities);
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

        List<Activity> activities = new ArrayList<>();
        for (Map<String, Object> activityMap : businesses) {
            this.activities.add(new Activity(activityMap));
        }
        logger.info("Activities fetched successfully.");
    }

    //GETTER

    public List<Activity> getActivities() {
        return this.activities;
    }
}
