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

    public ActivityService() {
        this.logger = Logger.getLogger(ActivityService.class.getName());
        this.activityRepository = new ActivityRepository();
        this.activities = new ArrayList<>();
        this.activityAPI = new ActivityAPI();
        this.limit = 5;
    }

    public void loadActivitiesFromApiByCity(String city) {
        this.activities.clear();
        this.activities.addAll(this.activityAPI.requestActivities(city, this.limit));
    }

    public void loadActivities() {
        this.activities.clear();
        this.activities.addAll(this.activityRepository.loadList());
    }

    public void persistActivities() {
        this.activityRepository.persistList(this.activities);
    }
}
