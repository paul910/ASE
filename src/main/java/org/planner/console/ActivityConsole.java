package org.planner.console;

import org.planner.service.ActivityService;

public class ActivityConsole implements ConsoleInterface{
    private ActivityService activityService;

    public ActivityConsole() {
        this.activityService = new ActivityService();
    }

    public void checkActivities() {
        String input = consoleHelper.readFromConsole("Enter city: ");
        this.activityService.fetchActivitiesByCity(input);
        if(this.activityService.getActivities().size() > 0) {
            this.printActivities();
        } else {
            consoleHelper.printError("No activities found for city: " + input + "!");
        }
    }
    public void printActivities() {
        String format = "%-30s%-10s%-20s%-40s%-20s%-50s%n";
        System.out.format(format, "Name", "Rating", "Price", "Address", "Phone", "URL");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
        this.activityService.getActivities().forEach(activity -> {
            System.out.format(format, activity.getName(), activity.getRating(), activity.getPrice(), activity.getAddress1(), activity.getPhone(), activity.getUrl());
        });
    }
}
