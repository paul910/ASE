package org.planner.console;

import org.planner.helper.ConsoleHelper;

import java.util.logging.Logger;

public interface ConsoleInterface {
    Logger logger = Logger.getLogger(ConsoleInterface.class.getName());
    ConsoleHelper consoleHelper = new ConsoleHelper();

}
