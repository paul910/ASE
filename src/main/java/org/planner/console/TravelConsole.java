package org.planner.console;

import org.planner.Debug;
import org.planner.api.ActivityAPI;
import org.planner.domain.Activity;
import org.planner.domain.Travel;
import org.planner.domain.User;
import org.planner.persistence.ActivityRepository;
import org.planner.service.ActivityService;
import org.planner.service.TravelService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TravelConsole implements ConsoleInterface {

    private final TravelService travelService;
    private Travel activeTravel;

    public TravelConsole(User user) {
        logger.setLevel(Debug.logLevel);

        this.travelService = new TravelService(user);
        this.activeTravel = null;
    }

    public void printTravelOverview() {
        if (this.travelService.getTravels().isEmpty()) {
            System.out.println("No travel planned yet.");
            return;
        }
        String format = "%-5s%-20s%-20s%-10s%-15s%-15s%-20s%-20s%-40s%n";
        System.out.printf(format, "ID", "Created By", "City", "Budget", "Start Date", "End Date", "Created Date", "Last Modified Date", "Activities");
        for (Travel travel : this.travelService.getTravels()) {
            System.out.printf(format, travel.getId(), travel.getCreatedBy(), travel.getCity(), travel.getBudget(), Travel.DATE_FORMAT.format(travel.getStartDate()), Travel.DATE_FORMAT.format(travel.getEndDate()), Travel.DATE_TIME_FORMAT.format(travel.getCreatedDate()), Travel.DATE_TIME_FORMAT.format(travel.getLastModifiedDate()), this.travelService.getActivitiesForTravelId(travel.getId()));
        }
    }

    public void addTravel() {
        Travel travel;
        try {
            String city = this.consoleHelper.readFromConsole("Enter city: ");
            Double budget = Double.parseDouble(this.consoleHelper.readFromConsole("Enter budget: "));
            Date startDate = Travel.DATE_FORMAT.parse(this.consoleHelper.readFromConsole("Enter start date (yyyy-MM-dd): "));
            Date endDate = Travel.DATE_FORMAT.parse(this.consoleHelper.readFromConsole("Enter end date (yyyy-MM-dd): "));
            travel = new Travel(this.travelService.getActiveUser(), city, budget, startDate, endDate);
        } catch (ParseException e) {
            consoleHelper.printError("Invalid input.");
            return;
        }
        consoleHelper.printSuccess("Travel added successfully.");
        this.travelService.addTravel(travel);
    }

    public void removeTravel() {
        this.printTravelOverview();
        String input = this.consoleHelper.readFromConsole("Enter travel id: ");
        Travel travel = this.travelService.getTravelById(input);
        if (travel == null) {
            logger.warning("Travel not found.");
            return;
        }
        consoleHelper.printSuccess("Travel removed successfully.");
        this.travelService.removeTravel(travel);
    }

    public void manageTravelActivities() {
        this.printTravelOverview();
        String input = this.consoleHelper.readFromConsole("Enter travel id: ");
        this.activeTravel = this.travelService.getTravelById(input);
        if (this.activeTravel == null) {
            logger.warning("Travel not found.");
            return;
        }

        input = this.consoleHelper.readFromConsole("1. Add activity to travel\n2. Remove activity from travel\n3. Back\nEnter: ");
        if (input.equals("1")) {
            this.addActivityToTravel();
        } else if (input.equals("2")) {
            this.removeActivityFromTravel();
        } else if (input.equals("3")) {
            // do nothing
        } else {
            consoleHelper.printError("Invalid input.");
        }
        this.activeTravel = null;
    }

    public void addActivityToTravel() {
        // load activities from API for active travel city
        ActivityService activityService = new ActivityService();
        activityService.fetchActivitiesByCity(this.activeTravel.getCity());
        List<Activity> activities = activityService.getActivities();
        if (activities.isEmpty()) {
            consoleHelper.printError("No activities found.");
            return;
        }

        ActivityConsole.printActivityOverview(activities);

        String input = consoleHelper.readFromConsole("Enter activity id, which you want to add to travel: ");
        for (Activity activity : activities) {
            if (activity.getId() == Long.parseLong(input)) {
                this.travelService.addActivityToTravel(this.activeTravel.getId(), activity.getId());
                consoleHelper.printSuccess("Activity added successfully.");
                return;
            }
        }
        consoleHelper.printError("Activity not found.");
    }

    public void removeActivityFromTravel() {
        List<Activity> activities = this.travelService.getActivitiesForTravelId(this.activeTravel.getId());
        if (activities.isEmpty()) {
            consoleHelper.printError("No activities found.");
            return;
        }

        ActivityConsole.printActivityOverview(activities);

        String input = consoleHelper.readFromConsole("Enter activity id, which you want to remove from travel: ");
        for (Activity activity : activities) {
            if (activity.getId() == Long.parseLong(input)) {
                this.travelService.removeActivityFromTravel(this.activeTravel.getId(), activity.getId());
                consoleHelper.printSuccess("Activity removed successfully.");
                return;
            }
        }
        consoleHelper.printError("Activity not found.");
    }
}
