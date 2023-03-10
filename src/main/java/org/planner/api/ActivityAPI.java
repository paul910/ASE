package org.planner.api;

import org.planner.domain.Activity;
import org.planner.helper.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ActivityAPI {

    private final Logger logger;
    private final String API_URL;
    private final String API_KEY;
    public ActivityAPI() {
        this.logger = Logger.getLogger(ActivityAPI.class.getName());
        this.API_URL = "https://api.yelp.com/v3/businesses/search";
        // this.API_KEY = System.getenv("YELP_API_KEY");
        this.API_KEY = "e7Ecbfhs5HnXLq8qCBQw7O_ZVXTNoE5f_u7Cg7jR-1QSNO0_ERR-U6zXEdWcfizyDocn_WiV39d1RtAavsMFbxXWzILZEVeQWLnU2gvEAg_GcStUFcaUAUchKRwKZHYx"; //TODO: remove this line
    }
    public List<Activity> requestActivities(String city, int limit) {
        StringBuilder response = null;
        String term = "attractions";
        String url = this.API_URL + "?location=" + URLEncoder.encode(city) + "&term=" + URLEncoder.encode(term) + "&limit=" + limit + "&sort_by=best_match";

        try {
            URL yelpEndpoint = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) yelpEndpoint.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + this.API_KEY);
            connection.setRequestProperty("Accept-Charset", "UTF-8");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String activityString = response.toString();
        // TODO: replace all the unicode characters with their actual characters
        // activityString = activityString.replaceAll("\\\\u0026", "&"); etc.

        Map<String, Object> obj = new JsonParser(activityString).parseObject();
        List<Map<String, Object>> businesses = (List<Map<String, Object>>) obj.get("businesses");

        List<Activity> activities = new ArrayList<>();
        for (Map<String, Object> activityMap : businesses) {
            activities.add(new Activity(activityMap));
        }

        logger.info("YelpAPI: " + activities.size() + " activities loaded successfully.");
        return activities;
    }
}
