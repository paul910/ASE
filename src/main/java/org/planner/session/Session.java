package org.planner.session;

import org.planner.domain.User;
import org.planner.domain.Weather;
import org.planner.helper.ConsoleHelper;
import org.planner.service.WeatherService;

import java.util.logging.Logger;

public class Session {
    private final Logger logger;
    private User activeUser;
    private final WeatherService weatherService;
    private final ConsoleHelper consoleHelper;

    public Session(User user) throws Exception {
        this.logger = Logger.getLogger(Session.class.getName());
        this.weatherService = new WeatherService();
        this.consoleHelper = new ConsoleHelper();
        this.activeUser = user;

        this.run();
    }

    private void run() throws Exception {
        while (this.activeUser != null) this.printMenu();
    }

    private void printMenu() throws Exception {
        String input = this.consoleHelper.readFromConsole("1. Add destination\n"
                + "2. Add activity\n"
                + "3. Add travel\n"
                + "4. Check weather\n"
                + "5. Logout\n"
                + "Enter: ");

        if (input.equals("1")) {
            // addDestination();
        } else if (input.equals("2")) {
            // addActivity();
        } else if (input.equals("3")) {
            // addTravel();
        } else if (input.equals("4")) {
            checkWeather();
        } else if (input.equals("5")) {
            this.logout();
            return;
        } else {
            this.logger.warning("Invalid option.");
        }
        this.printMenu();
    }

    private void checkWeather() throws Exception {
        String input = this.consoleHelper.readFromConsole("Enter city: ");
        Weather weather = weatherService.getWeather(input);
        if (weather == null) {
            logger.warning("Weather request failed.");
            return;
        }
        this.consoleHelper.printSuccess("The current weather in " + input + " is as follows:");
        this.consoleHelper.print(weather.getInfoText());
    }

    private void logout() throws Exception {
        this.activeUser = null;
        this.logger.info("Successfully logged out.");
        this.consoleHelper.printSuccess("Successfully logged out.");
    }
}
