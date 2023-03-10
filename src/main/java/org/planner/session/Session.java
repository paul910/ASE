package org.planner.session;

import org.planner.domain.Activity;
import org.planner.domain.Travel;
import org.planner.domain.User;
import org.planner.domain.Weather;
import org.planner.helper.ConsoleHelper;
import org.planner.service.ActivityService;
import org.planner.service.TravelService;
import org.planner.service.WeatherService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Session {
    private final Logger logger;
    private final ConsoleHelper consoleHelper;
    private final WeatherService weatherService;
    private final TravelService travelService;
    private final ActivityService activityService;
    private User activeUser;

    public Session(User user) {
        this.activeUser = user;
        this.logger = Logger.getLogger(Session.class.getName());
        this.consoleHelper = new ConsoleHelper();
        this.weatherService = new WeatherService();
        this.travelService = new TravelService(this.activeUser);
        this.activityService = new ActivityService();

        this.run();
    }

    private void run() {
        while (this.activeUser != null) this.printMenu();
    }

    private void printMenu() {
        String input = this.consoleHelper.readFromConsole("1. Get travel overview\n" + "2. Add travel\n" + "3. Remove travel\n" + "4. Check weather\n" + "5. Check activities\n" + "6. Logout\n" + "Enter: ");

        if (input.equals("1")) {
            getTravelOverview();
        } else if (input.equals("2")) {
            addTravel();
        } else if (input.equals("3")) {
            removeTravel();
        } else if (input.equals("4")) {
            checkWeather();
        } else if (input.equals("5")) {
            checkActivities();
        } else if (input.equals("6")) {
            this.logout();
            return;
        } else {
            this.logger.warning("Invalid option.");
        }
        this.printMenu();
    }

    private void getTravelOverview() {
        this.travelService.printTravelList();
    }

    private void addTravel() {
        Travel travel = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String city = this.consoleHelper.readFromConsole("Enter city: ");
            Double budget = Double.parseDouble(this.consoleHelper.readFromConsole("Enter budget: "));
            Date startDate = dateFormat.parse(this.consoleHelper.readFromConsole("Enter start date (yyyy-MM-dd HH:mm:ss): "));
            Date endDate = dateFormat.parse(this.consoleHelper.readFromConsole("Enter end date (yyyy-MM-dd HH:mm:ss): "));
            travel = new Travel(this.activeUser, city, budget, startDate, endDate);
        } catch (ParseException e) {
            logger.warning("Invalid input.");
        }
        if (travel == null) return;
        this.travelService.addTravel(travel);
    }

    private void removeTravel() {
        this.travelService.printTravelList();
        String input = this.consoleHelper.readFromConsole("Enter travel id: ");
        Travel travel = this.travelService.getTravelById(input);
        if (travel == null) {
            this.logger.warning("Travel not found.");
            return;
        }
        this.travelService.removeTravel(travel);
    }

    private void checkWeather() {
        String input = this.consoleHelper.readFromConsole("Enter city: ");
        Weather weather = weatherService.getWeather(input);
        if (weather == null) {
            logger.warning("Weather request failed.");
            return;
        }
        this.consoleHelper.printSuccess("The current weather in " + input + " is as follows:");
        this.consoleHelper.print(weather.getInfoText());
    }

    private void checkActivities() {
        String input = this.consoleHelper.readFromConsole("Enter city: ");
        activityService.loadActivitiesFromApiByCity(input);
        activityService.printActivities();
    }

    private void logout() {
        this.activeUser = null;
        this.logger.info("Successfully logged out.");
        this.consoleHelper.printSuccess("Successfully logged out.");
    }
}
