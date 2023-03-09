package org.planner.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.planner.api.WeatherAPI;
import org.planner.domain.Weather;

public class WeatherServiceTest {

    private WeatherAPI weatherAPI;
    private WeatherService weatherService;

    @BeforeEach
    public void setup() {
        weatherAPI = mock(WeatherAPI.class);
        weatherService = new WeatherService("test_credentials");
        weatherService.setWeatherAPI(weatherAPI);
    }

    @Test
    public void testGetWeatherReturnsWeather() throws Exception {
        String weatherData = "sunny";
        when(weatherAPI.getWeatherData("Berlin")).thenReturn(weatherData);

        Weather weather = weatherService.getWeather("Berlin");

        assertEquals(new Weather(weatherData), weather);
    }

    @Test
    public void testGetWeatherReturnsNullWhenResponseIsNull() throws Exception {
        when(weatherAPI.getWeatherData("Paris")).thenReturn(null);

        Weather weather = weatherService.getWeather("Paris");

        assertEquals(null, weather);
    }
}
