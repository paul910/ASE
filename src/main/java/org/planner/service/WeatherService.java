package org.planner.service;

import org.planner.api.WeatherAPI;
import org.planner.domain.Weather;
import org.planner.helper.JsonParser;

import java.util.Map;

public class WeatherService {
    private WeatherAPI weatherAPI;
    private Weather weather;

    public WeatherService() {
        this.weatherAPI = new WeatherAPI();
        this.weather = null;
    }

    public void fetchWeatherByCity(String city) {
        String response = weatherAPI.request(city);
        if (response == null) {
            return;
        }

        Map<String, Object> obj = new JsonParser(response).parseObject();
        this.weather = new Weather(obj);
    }

    public Weather getWeather() {
        return weather;
    }
}

