package org.planner.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class ActivityAPI implements APIInterface{
    private final String API_URL;
    private final String API_KEY;

    public ActivityAPI() {
        this.API_URL = "https://api.yelp.com/v3/businesses/search";
        this.API_KEY = System.getenv("YELP_API_KEY");
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
        return response.toString();
    }
}
