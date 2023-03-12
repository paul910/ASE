package org.planner.api;

import java.util.logging.Logger;
public interface APIInterface {
    Logger logger = Logger.getLogger(APIInterface.class.getName());
    String request(String city);
}
