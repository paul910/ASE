package org.planner.session;

import org.planner.console.ConsoleInterface;
import org.planner.domain.User;
import org.planner.service.UserService;
import org.planner.service.UserServiceDecorator;

public class Start implements ConsoleInterface {
    private final UserServiceDecorator userServiceDecorator;

    public Start() {
        this.userServiceDecorator = new UserServiceDecorator(new UserService());

        consoleHelper.printWelcomeLogo();
        while (true) {
            this.printMenu();
        }
    }

    public static void main(String[] args) {
        new Start();
    }

    private void printMenu() {
        String input = consoleHelper.readFromConsole("1. Register new user\n" + "2. Login\n" + "3. Exit\n" + "Enter: ");

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
        String username = consoleHelper.readFromConsole("Enter username: ");
        String password = consoleHelper.readFromConsole("Enter password: ");
        String email = consoleHelper.readFromConsole("Enter email: ");

        User user = this.userServiceDecorator.createUser(username, password, email);
        if (user != null) {
            consoleHelper.printSuccess("Successfully registered.\nLogin now.");
            this.login();
        } else {
            consoleHelper.printError("Registration failed.");
            this.printMenu();
        }
    }

    private void login() {
        String username = consoleHelper.readFromConsole("Enter username: ");
        String password = consoleHelper.readFromConsole("Enter password: ");

        User user = this.userServiceDecorator.login(username, password);

        if (user != null) {
            consoleHelper.printSuccess("Successfully logged in.");
            consoleHelper.printBlue("Welcome " + user.getUsername() + "!");
            new Session(user).run();
        } else {
            consoleHelper.printError("Login failed.");
            this.printMenu();
        }
    }
}
