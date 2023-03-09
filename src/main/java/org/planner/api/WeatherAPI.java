package org.planner.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

public class WeatherAPI {
    private String API_URL = "https://api.openweathermap.org/data/2.5/weather?units=metric";
    private String API_KEY;

    private Logger logger;

    public WeatherAPI() {
        this.logger = Logger.getLogger(WeatherAPI.class.getName());
        this.API_KEY = System.getenv("WEATHER_API_KEY");
    }

    public String getWeatherData(String city) throws IOException {
        URL obj = new URL(API_URL + "&q=" + city + "&appid=" + API_KEY);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode != 200) {
            logger.warning("GET request not worked. Response code: " + responseCode);
            return null;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
}
