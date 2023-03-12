package org.planner.session;

import org.planner.domain.User;
import org.planner.helper.ConsoleHelper;
import org.planner.service.UserService;

import java.util.logging.Logger;

public class Start {
    private final Logger logger;
    private final ConsoleHelper consoleHelper;
    private final UserService userService;
    private Session session;

    public Start() {
        this.logger = Logger.getLogger(Start.class.getName());
        this.consoleHelper = new ConsoleHelper();
        this.userService = new UserService();

        consoleHelper.printWelcomeLogo();
        while (true) {
            this.session = null;
            this.printMenu();
        }
    }

    private void printMenu() {
        String input = this.consoleHelper.readFromConsole("1. Register new user\n" + "2. Login\n" + "3. Exit\n" + "Enter: ");

        if (input.equals("1")) {
            register();
        } else if (input.equals("2")) {
            login();
        } else if (input.equals("3")) {
            consoleHelper.printPurple("Goodbye!");
            System.exit(0);
        } else {
            this.logger.warning("Invalid option.");
        }
    }

    private void register() {
        String username = this.consoleHelper.readFromConsole("Enter username: ");
        String password = this.consoleHelper.readFromConsole("Enter password: ");
        String email = this.consoleHelper.readFromConsole("Enter email: ");

        User user = this.userService.createUser(username, password, email);
        if (user != null) {
            this.consoleHelper.printSuccess("Successfully registered.\nLogin now.");
            this.login();
        } else {
            this.consoleHelper.printError("Registration failed.");
            this.printMenu();
        }
    }

    private void login() {
        String username = this.consoleHelper.readFromConsole("Enter username: ");
        String password = this.consoleHelper.readFromConsole("Enter password: ");

        User user = this.userService.login(username, password);

        if (user != null) {
            this.consoleHelper.printSuccess("Successfully logged in.");
            consoleHelper.printBlue("Welcome " + user.getUsername() + "!\n");
            this.session = new Session(user);
        } else {
            this.consoleHelper.printError("Login failed.");
            this.printMenu();
        }
    }
}
