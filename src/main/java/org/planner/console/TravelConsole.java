package org.planner.console;

import org.planner.domain.Travel;
import org.planner.service.TravelService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TravelConsole implements ConsoleInterface {

    private final TravelService travelService;

    public TravelConsole(TravelService travelService) {
        this.travelService = travelService;
    }

    public void printTravelOverview() {
        if (this.travelService.getTravels().isEmpty()) {
            System.out.println("No travel planned yet.");
            return;
        }
        System.out.printf("%-5s%-20s%-20s%-10s%-20s%-20s%-20s%-20s%n", "ID", "Created By", "City", "Budget", "Start Date", "End Date", "Created Date", "Last Modified Date");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Travel travel : this.travelService.getTravels()) {
            System.out.printf("%-5d%-20s%-20s%-10.2f%-20s%-20s%-20s%-20s%n", travel.getId(), travel.getCreatedBy(), travel.getCity(), travel.getBudget(), dateFormat.format(travel.getStartDate()), dateFormat.format(travel.getEndDate()), dateFormat.format(travel.getCreatedDate()), dateFormat.format(travel.getLastModifiedDate()));
        }
    }

    public void addTravel() {
        Travel travel;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String city = this.consoleHelper.readFromConsole("Enter city: ");
            Double budget = Double.parseDouble(this.consoleHelper.readFromConsole("Enter budget: "));
            Date startDate = dateFormat.parse(this.consoleHelper.readFromConsole("Enter start date (yyyy-MM-dd HH:mm:ss): "));
            Date endDate = dateFormat.parse(this.consoleHelper.readFromConsole("Enter end date (yyyy-MM-dd HH:mm:ss): "));
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
}
