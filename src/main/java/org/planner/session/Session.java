package org.planner.session;

import org.planner.console.*;
import org.planner.domain.User;

public class Session implements ConsoleInterface {
    private TravelConsole travelConsole;
    private WeatherConsole weatherConsole;
    private ActivityConsole activityConsole;
    private User activeUser;

    public Session(User user) {
        this.activeUser = user;

        this.travelConsole = new TravelConsole(this.activeUser);
        this.weatherConsole = new WeatherConsole();
        this.activityConsole = new ActivityConsole();

        this.run();
    }

    private void run() {
        while (this.activeUser != null) this.printMenu();
    }

    private void printMenu() {
        String input = consoleHelper.readFromConsole("1. Get travel overview\n" + "2. Add travel\n" + "3. Remove travel\n" + "4. Manage travel activities\n" + "5. Check weather\n" + "6. Check activities\n" + "7. Logout\n" + "Enter: ");

        if (input.equals("1")) {
            this.travelConsole.printTravelOverview();
        } else if (input.equals("2")) {
            this.travelConsole.addTravel();
        } else if (input.equals("3")) {
            this.travelConsole.removeTravel();
        } else if (input.equals("4")) {
            this.travelConsole.manageTravelActivities();
        } else if (input.equals("5")) {
            this.weatherConsole.checkWeather();
        } else if (input.equals("6")) {
            this.activityConsole.checkActivities();
        } else if (input.equals("7")) {
            this.logout();
            return;
        } else {
            this.consoleHelper.printError("Invalid input.");
        }
        this.refresh();
    }

    public void refresh() {
        this.travelConsole = new TravelConsole(this.activeUser);

        this.weatherConsole = new WeatherConsole();
        this.activityConsole = new ActivityConsole();
    }

    private void logout() {
        this.activeUser = null;
        this.consoleHelper.printSuccess("Successfully logged out.");
    }
}
