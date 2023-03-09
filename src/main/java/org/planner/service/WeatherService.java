package org.planner.service;

import org.planner.api.WeatherAPI;
import org.planner.domain.Weather;

public class WeatherService {
    private WeatherAPI weatherAPI;

    public WeatherService() {
        this.weatherAPI = new WeatherAPI();
    }

    public Weather getWeather(String city) {
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

