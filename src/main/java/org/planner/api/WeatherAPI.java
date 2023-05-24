package org.planner.api;

import org.planner.Debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherAPI implements APIInterface {
    private final String API_URL;
    private final String API_KEY;

    public WeatherAPI() {
        logger.setLevel(Debug.logLevel);

        this.API_URL = "https://api.openweathermap.org/data/2.5/weather?units=metric";
        this.API_KEY = "e7Ecbfhs5HnXLq8qCBQw7O_ZVXTNoE5f_u7Cg7jR-1QSNO0_ERR-U6zXEdWcfizyDocn_WiV39d1RtAavsMFbxXWzILZEVeQWLnU2gvEAg_GcStUFcaUAUchKRwKZHYx";
    }

    @Override
    public String request(String city) {
        StringBuffer response = null;
        try {
            URL url = new URL(this.API_URL + "&q=" + URLEncoder.encode(city) + "&appid=" + this.API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                logger.warning("GET request not worked. Response code: " + responseCode);
                return null;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            logger.warning("GET request not worked.");
        }
        return response.toString();
    }
}
