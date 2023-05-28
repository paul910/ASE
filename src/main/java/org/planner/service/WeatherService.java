package org.planner.service;

import org.planner.api.WeatherAPI;
import org.planner.domain.Weather;
import org.planner.domain.WeatherBuilder;
import org.planner.helper.JsonParser;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WeatherService {
    private final WeatherAPI weatherAPI;
    private Weather weather;

    public WeatherService() {
        this.weatherAPI = new WeatherAPI();
        this.weather = null;
    }

    //API

    public void fetchWeatherByCity(String city) {
        String response = weatherAPI.request(city);
        if (response == null) {
            return;
        }

        Map<String, Object> obj = new JsonParser(response).parseObject();

        WeatherBuilder weatherBuilder = new WeatherBuilder();

        Map<String, Object> weather = (Map<String, Object>) ((List<Object>) obj.get("weather")).get(0);
        {
            weatherBuilder.setMain((String) weather.get("main"));
            weatherBuilder.setDescription((String) weather.get("description"));
        }
        Map<String, Object> main = (Map<String, Object>) obj.get("main");
        {
            weatherBuilder.setTemp((double) main.get("temp"));
            weatherBuilder.setFeelsLike((double) main.get("feels_like"));
            weatherBuilder.setPressure(Long.valueOf((long) main.get("pressure")).intValue());
            weatherBuilder.setHumidity(Long.valueOf((long) main.get("humidity")).intValue());
            weatherBuilder.setTempMin((double) main.get("temp_min"));
            weatherBuilder.setTempMax((double) main.get("temp_max"));
        }
        weatherBuilder.setVisibility(Long.valueOf((long) obj.get("visibility")).intValue());
        Map<String, Object> wind = (Map<String, Object>) obj.get("wind");
        {
            weatherBuilder.setWindSpeed((double) (wind.get("speed") != null ? wind.get("speed") : 0.0));
            weatherBuilder.setWindDeg(Long.valueOf((long) wind.get("deg")).intValue());
            weatherBuilder.setWindGust((double) (wind.get("gust") != null ? wind.get("gust") : 0.0));
        }
        Map<String, Object> clouds = (Map<String, Object>) obj.get("clouds");
        {
            weatherBuilder.setCloudsAll(Long.valueOf((long) clouds.get("all")).intValue());
        }
        Map<String, Object> sys = (Map<String, Object>) obj.get("sys");
        {
            weatherBuilder.setSysSunrise(new Date((long) sys.get("sunrise") * 1000));
            weatherBuilder.setSysSunset(new Date((long) sys.get("sunset") * 1000));
        }

        this.weather = weatherBuilder.build();
    }

    public Weather getWeather() {
        return weather;
    }
}

