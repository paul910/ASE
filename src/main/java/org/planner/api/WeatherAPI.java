package org.planner.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Logger;

public class WeatherAPI {
    private final Logger logger;
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?units=metric";
    private final String API_KEY;

    public WeatherAPI() {
        this.logger = Logger.getLogger(WeatherAPI.class.getName());
        this.API_KEY = System.getenv("WEATHER_API_KEY");
    }

    public String getWeatherData(String city) {
        StringBuffer response;
        try {
            URL url = new URL(API_URL + "&q=" + city + "&appid=" + API_KEY);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            if (responseCode != 200) {
                logger.warning("GET request not worked. Response code: " + responseCode);
                return null;
            }
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response.toString();
    }
}
