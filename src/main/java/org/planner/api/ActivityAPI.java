package org.planner.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.planner.Debug;

public class ActivityAPI implements APIInterface {
    private final String API_URL;
    private final String API_KEY;

    public ActivityAPI() {
        logger.setLevel(Debug.logLevel);

        this.API_URL = "https://api.yelp.com/v3/businesses/search";
        this.API_KEY = "e7Ecbfhs5HnXLq8qCBQw7O_ZVXTNoE5f_u7Cg7jR-1QSNO0_ERR-U6zXEdWcfizyDocn_WiV39d1RtAavsMFbxXWzILZEVeQWLnU2gvEAg_GcStUFcaUAUchKRwKZHYx";
    }

    @Override
    public String request(String city) {
        StringBuilder response = null;
        try {
            URL url = new URL(this.API_URL + "?location=" + URLEncoder.encode(city) + "&term=attractions&limit=10&sort_by=best_match");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + this.API_KEY);

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                logger.warning("GET request not worked. Response code: " + responseCode);
                return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
        } catch (IOException e) {
            logger.warning("GET request not worked.");
        }
        // TODO: replace all the unicode characters with their actual characters
        // activityString = activityString.replaceAll("\\\\u0026", "&"); etc.
        return response.toString();
    }
}
