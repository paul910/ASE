package org.planner.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class ConsoleHelper {
    private final Logger logger;
    private final BufferedReader reader;

    public ConsoleHelper() {
        this.logger = Logger.getLogger(ConsoleHelper.class.getName());
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readFromConsole(String message) {
        System.out.print(ConsoleColors.RESET.getColorValue() + "" + message);
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            logger.warning("Error while reading from console.");
        }
        System.out.print(ConsoleColors.BLACK.getColorValue());
        return input;
    }

    public void printError(String message) {
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
        System.out.println(ConsoleColors.BLUE_UNDERLINED.getColorValue() + "Welcome to Travel Planner!\n" + ConsoleColors.BLUE.getColorValue() + "    ________________\n" + "   /                \\\n" + "  |  TRAVEL PLANNER  |\n" + "   \\________________/\n" + ConsoleColors.BLACK.getColorValue());
    }

    public void printBlockText(String text) {
        String[] words = text.split("\\s+");
        StringBuilder line = new StringBuilder();
        int maxWidth = 80;
        String horizontalLine = "+" + "-".repeat(maxWidth) + "+";
        System.out.println(horizontalLine);
        for (String word : words) {
            if (line.length() + word.length() + 1 > maxWidth) {
                System.out.printf("| %-" + (maxWidth - 2) + "s |\n", line);
                line = new StringBuilder();
            }
            line.append(word).append(" ");
        }
        if (line.length() > 0) {
            System.out.printf("| %-" + (maxWidth - 2) + "s |\n", line);
        }
        System.out.println(horizontalLine);
    }
}
