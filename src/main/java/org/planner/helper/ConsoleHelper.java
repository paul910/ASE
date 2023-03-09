package org.planner.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private BufferedReader reader;

    public ConsoleHelper() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readFromConsole(String message) throws IOException {
        System.out.print(ConsoleColors.RESET.getColorValue() + "" + message);
        String input = reader.readLine();
        System.out.print(ConsoleColors.BLACK.getColorValue());
        return input;
    }

    public void printErr(String message) {
        System.out.println(ConsoleColors.RED.getColorValue() + message + ConsoleColors.BLACK.getColorValue());
    }

    public void printSuccess(String message) {
        System.out.println(ConsoleColors.GREEN.getColorValue() + message + ConsoleColors.BLACK.getColorValue());
    }

    public void print(String message) {
        System.out.println(ConsoleColors.RESET.getColorValue() + message + ConsoleColors.BLACK.getColorValue());
    }

    public void printBlue(String message) {
        System.out.println(ConsoleColors.BLUE.getColorValue() + message + ConsoleColors.BLACK.getColorValue());
    }

    public void printPurple(String message) {
        System.out.println(ConsoleColors.PURPLE.getColorValue() + message + ConsoleColors.BLACK.getColorValue());
    }

    public void printWelcomeLogo() {
        System.out.println(ConsoleColors.BLUE_UNDERLINED.getColorValue()
                + "Welcome to Travel Planner!\n"
                + ConsoleColors.BLUE.getColorValue()
                + "    ________________\n"
                + "   /                \\\n"
                + "  |  TRAVEL PLANNER  |\n"
                + "   \\________________/\n"
                + ConsoleColors.BLACK.getColorValue());
    }
}
