package org.planner.domain;

import java.util.ArrayList;
import java.util.List;

public class Destination {
    private String city;
    private final List<Activity> activities;

    public Destination(String city) {
        this.city = city;
        this.activities = new ArrayList<>();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }
}