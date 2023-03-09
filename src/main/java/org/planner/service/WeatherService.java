package org.planner.service;

import org.planner.api.WeatherAPI;
import org.planner.domain.Weather;

import java.util.logging.Logger;

public class WeatherService {
    private final Logger logger;
    private WeatherAPI weatherAPI;

    public WeatherService() {
        this.logger = Logger.getLogger(WeatherService.class.getName());
        this.weatherAPI = new WeatherAPI();
    }

    public Weather getWeather(String city) throws Exception {
        String response = weatherAPI.getWeatherData(city);
        if (response == null) {
            return null;
        }
        return new Weather(response);
    }

    public void setWeatherAPI(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }
}

