package org.planner.service;

import org.planner.api.ActivityAPI;
import org.planner.domain.Activity;
import org.planner.persistence.ActivityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ActivityService {
    private final Logger logger;
    private final ActivityRepository activityRepository;
    private final List<Activity> activities;
    private final ActivityAPI activityAPI;
    private final int limit;

    public ActivityService(String rootPath) {
        this.logger = Logger.getLogger(ActivityService.class.getName());
        this.activityRepository = new ActivityRepository(rootPath);
        this.activities = new ArrayList<>();
        this.activityAPI = new ActivityAPI();
        this.limit = 5;
    }

    public static void main(String[] args) {
        ActivityService activityService = new ActivityService("src/main/resources");
        activityService.loadActivitiesFromApiByCity("Wiesloch");
        activityService.persistActivities();
    }

    public void loadActivitiesFromApiByCity(String city) {
        this.activities.clear();
        this.activities.addAll(this.activityAPI.requestActivities(city, this.limit));
    }

    public void loadActivities() {
        this.activities.clear();
        this.activities.addAll(this.activityRepository.loadActivityList());
    }

    public void persistActivities() {
        this.activityRepository.persistActivityList(this.activities);
    }
}
