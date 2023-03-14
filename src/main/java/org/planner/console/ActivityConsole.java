package org.planner.console;

import org.planner.domain.Activity;
import org.planner.service.ActivityService;

import java.util.List;

public class ActivityConsole implements ConsoleInterface {
    private final ActivityService activityService;

    public ActivityConsole() {
        this.activityService = new ActivityService();
    }

    public static void printActivityOverview(List<Activity> activities) {
        String format = "%-10s%-30s%-10s%-20s%-40s%-20s%-50s%n";
        System.out.format(format, "ID", "Name", "Rating", "Price", "Address", "Phone", "URL");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
        activities.forEach(activity -> {
            System.out.format(format, activity.getId(), activity.getName(), activity.getRating(), activity.getPrice(), activity.getAddress1(), activity.getPhone(), activity.getUrl());
        });
    }

    public void checkActivities() {
        String input = consoleHelper.readFromConsole("Enter city: ");
        this.activityService.fetchActivitiesByCity(input);
        if (this.activityService.getActivities().size() > 0) {
            this.printActivities();
        } else {
            consoleHelper.printError("No activities found for city: " + input + "!");
        }
    }

    public void printActivities() {
        printActivityOverview(this.activityService.getActivities());
    }
}
