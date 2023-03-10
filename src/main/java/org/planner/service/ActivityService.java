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

    public void printActivities() {
        // Print header row
        System.out.println("|   id  |    alias    |              name             |              image_url             | rating | review_count | price |    country    |         state        |         city         |              address1             |              address2             |              address3             | zip_code |  latitude  | longitude  |                 url                 |        phone        |      display_phone     |");
        System.out.println("+-------+-------------+--------------------------------+------------------------------------+--------+--------------+-------+---------------+---------------------+----------------------+------------------------------------+------------------------------------+------------------------------------+----------+------------+------------+-------------------------------------+---------------------+------------------------+");

        // Print rows
        for (Activity activity : this.activities) {
            System.out.printf("| %5s | %11s | %30s | %34s | %6.2f | %12d | %5s | %13s | %20s | %20s | %34s | %34s | %34s | %8s | %10.6f | %10.6f | %35s | %19s | %22s |\n",
                    activity.getId(),
                    activity.getAlias(),
                    activity.getName(),
                    activity.getImage_url(),
                    activity.getRating(),
                    activity.getReviewCount(),
                    activity.getPrice(),
                    activity.getCountry(),
                    activity.getState(),
                    activity.getCity(),
                    activity.getAddress1(),
                    activity.getAddress2(),
                    activity.getAddress3(),
                    activity.getZipCode(),
                    activity.getLatitude(),
                    activity.getLongitude(),
                    activity.getUrl(),
                    activity.getPhone(),
                    activity.getDisplay_phone()
            );
        }

        // Print footer row
        System.out.println("+-------+-------------+--------------------------------+------------------------------------+--------+--------------+-------+---------------+---------------------+----------------------+------------------------------------+------------------------------------+------------------------------------+----------+------------+------------+-------------------------------------+---------------------+------------------------+");
    }

}
