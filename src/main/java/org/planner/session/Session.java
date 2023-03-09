package org.planner.session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import org.planner.domain.User;
import org.planner.domain.Weather;
import org.planner.service.ConsoleColors;
import org.planner.service.UserService;
import org.planner.service.WeatherService;

public class Session {
    private Logger logger;
    private BufferedReader reader;
    private User activeUser;
    private UserService userService;
    private WeatherService weatherService;

    public Session(String credentials) throws NoSuchAlgorithmException, IOException {
        logger = Logger.getLogger(Session.class.getName());
        reader = new BufferedReader(new InputStreamReader(System.in));
        userService = new UserService("src/main/resources");
        weatherService = new WeatherService(credentials);
        activeUser = null;

        this.welcome();
    }

    public void run() throws Exception {
        while (true) {
            this.printMenu();
        }
    }

    private void printInternalMenu() throws Exception {
        String input = this.readFromConsole("1. Add destination\n"
                + "2. Add activity\n"
                + "3. Add travel\n"
                + "4. Check weather\n"
                + "5. Logout\n"
                + "Enter: ");
        switch (input) {
            case "1":
                // addDestination();
                break;
            case "2":
                // addActivity();
                break;
            case "3":
                // addTravel();
                break;
            case "4":
                checkWeather();
                break;
            case "5":
                this.logout();
                return;
            default:
                logger.warning("Invalid option.");
                printInternalMenu();
                break;
        }
        printInternalMenu();
    }

    private void checkWeather() throws Exception {
        String input = this.readFromConsole("Enter city: ");
        Weather weather = weatherService.getWeather(input);
        if (weather == null) {
            logger.warning("Weather request failed.");
            return;
        }
        printSuccess("The current weather in " + input + " is as follows:");
        print(weather.getInfoText());
    }

    private void printMenu() throws Exception {
        String input = this.readFromConsole("1. Register new user\n"
                + "2. Login\n"
                + "3. Exit\n"
                + "Enter: ");
        switch (input) {
            case "1":
                registerUser();
                break;
            case "2":
                login();
                break;
            case "3":
                System.out.println(ConsoleColors.PURPLE.getColorValue() + "Goodbye!" + ConsoleColors.RESET.getColorValue());
                System.exit(0);
                break;
            default:
                logger.warning("Invalid option.");
                break;
        }
    }

    private void registerUser() throws Exception {
        String username = this.readFromConsole("Enter username: ");
        String password = this.readFromConsole("Enter password: ");
        String email = this.readFromConsole("Enter email: ");

        this.activeUser = this.userService.createUser(username, password, email);
        if (this.activeUser != null) {
            this.printSuccess("Successfully registered.");
            this.printInternalMenu();
        } else {
            this.printErr("Registration failed.");
            this.printMenu();
        }
    }

    private void login() throws Exception {
        String username = readFromConsole("Enter username: ");
        String password = readFromConsole("Enter password: ");

        this.activeUser = this.userService.login(username, password);

        if (this.activeUser != null) {
            this.printSuccess("Successfully logged in.");
            System.out.println(
                    ConsoleColors.BLUE.getColorValue() + "Welcome " + this.activeUser.getUsername() + "!\n" + ConsoleColors.RESET.getColorValue());
            this.printInternalMenu();
        } else {
            this.printErr("Login failed.");
            this.printMenu();
        }
    }

    private void logout() throws Exception {
        this.activeUser = null;
        this.logger.info("Successfully logged out.");
        this.printSuccess("Successfully logged out.");
    }

    private String readFromConsole(String message) throws IOException {
        System.out.print(ConsoleColors.RESET.getColorValue() + "" + message);
        String input = reader.readLine();
        System.out.print(ConsoleColors.BLACK.getColorValue());
        return input;
    }

    private void printErr(String message) {
        System.out.println(ConsoleColors.RED.getColorValue() + message + ConsoleColors.BLACK.getColorValue());
    }

    private void printSuccess(String message) {
        System.out.println(ConsoleColors.GREEN.getColorValue() + message + ConsoleColors.BLACK.getColorValue());
    }

    private void print(String message) {
        System.out.println(ConsoleColors.RESET.getColorValue() + message + ConsoleColors.BLACK.getColorValue());
    }

    private void welcome() {
        System.out.println(ConsoleColors.BLUE_UNDERLINED.getColorValue() + "Welcome to Travel Planner!\n" + ConsoleColors.BLUE.getColorValue()
                + "    ________________\n"
                + "   /                \\\n"
                + "  |  TRAVEL PLANNER  |\n"
                + "   \\________________/\n" + ConsoleColors.BLACK.getColorValue());
    }
}
