package org.planner.session;

import org.planner.console.ActivityConsole;
import org.planner.console.ConsoleInterface;
import org.planner.console.TravelConsole;
import org.planner.console.WeatherConsole;
import org.planner.domain.User;
import org.planner.service.TravelService;

public class Session implements ConsoleInterface {
    private final TravelService travelService;
    private final TravelConsole travelConsole;
    private final WeatherConsole weatherConsole;
    private final ActivityConsole activityConsole;
    private User activeUser;

    public Session(User user) {
        this.activeUser = user;
        this.travelService = new TravelService(this.activeUser);
        this.travelConsole = new TravelConsole(this.travelService);

        this.weatherConsole = new WeatherConsole();
        this.activityConsole = new ActivityConsole();

        this.run();
    }

    private void run() {
        while (this.activeUser != null) this.printMenu();
    }

    private void printMenu() {
        String input = consoleHelper.readFromConsole("1. Get travel overview\n" + "2. Add travel\n" + "3. Remove travel\n" + "4. Check weather\n" + "5. Check activities\n" + "6. Logout\n" + "Enter: ");

        if (input.equals("1")) {
            this.travelConsole.printTravelOverview();
        } else if (input.equals("2")) {
            this.travelConsole.addTravel();
        } else if (input.equals("3")) {
            this.travelConsole.removeTravel();
        } else if (input.equals("4")) {
            this.weatherConsole.checkWeather();
        } else if (input.equals("5")) {
            this.activityConsole.checkActivities();
        } else if (input.equals("6")) {
            this.logout();
            return;
        } else {
            this.consoleHelper.printError("Invalid input.");
        }
        this.printMenu();
    }

    private void logout() {
        this.activeUser = null;
        this.consoleHelper.printSuccess("Successfully logged out.");
    }
}
