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
        weatherService = new WeatherService();
        weatherService.setWeatherAPI(weatherAPI);
    }

    @Test
    public void testGetWeather() throws Exception {
        String weatherData = "{\"coord\":{\"lon\":-122.08,\"lat\":37.39},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":282.55,\"feels_like\":281.86,\"temp_min\":280.37,\"temp_max\":284.26,\"pressure\":1023,\"humidity\":100},\"visibility\":16093,\"wind\":{\"speed\":1.5,\"deg\":350, \"gust\":2.68},\"clouds\":{\"all\":1},\"dt\":1560350645,\"sys\":{\"type\":1,\"id\":5122,\"message\":0.0139,\"country\":\"US\",\"sunrise\":1560343627,\"sunset\":1560396563},\"timezone\":-25200,\"id\":420006353,\"name\":\"Mountain View\",\"cod\":200}";
        when(weatherAPI.getWeatherData("Berlin")).thenReturn(weatherData);
        Weather weather = weatherService.getWeather("Berlin");
        assertEquals(new Weather(weatherData).toString(), weather.toString());
    }
}
