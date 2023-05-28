package org.planner.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.planner.helper.JsonParser;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeatherTest {

    private Weather weather;

    @BeforeEach
    void setUp() {
        String jsonString = "{\"coord\":{\"lon\":-122.08,\"lat\":37.39},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}],\"base\":\"stations\",\"main\":{\"temp\":282.55,\"feels_like\":281.86,\"temp_min\":280.37,\"temp_max\":284.26,\"pressure\":1023,\"humidity\":100},\"visibility\":16093,\"wind\":{\"speed\":1.5,\"deg\":350, \"gust\":2.68},\"clouds\":{\"all\":1},\"dt\":1560350645,\"sys\":{\"type\":1,\"id\":5122,\"message\":0.0139,\"country\":\"US\",\"sunrise\":1560343627,\"sunset\":1560396563},\"timezone\":-25200,\"id\":420006353,\"name\":\"Mountain View\",\"cod\":200}";

        Map<String, Object> obj = new JsonParser(jsonString).parseObject();

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

    @Test
    void testGetMain() {
        assertEquals("Clear", weather.getMain());
    }

    @Test
    void testGetDescription() {
        assertEquals("clearsky", weather.getDescription());
    }

    @Test
    void testGetTemp() {
        assertEquals(282.55, weather.getTemp(), 0.01);
    }

    @Test
    void testGetFeelsLike() {
        assertEquals(281.86, weather.getFeelsLike(), 0.01);
    }

    @Test
    void testGetPressure() {
        assertEquals(1023, weather.getPressure());
    }

    @Test
    void testGetHumidity() {
        assertEquals(100, weather.getHumidity());
    }

    @Test
    void testGetTempMin() {
        assertEquals(280.37, weather.getTempMin(), 0.01);
    }

    @Test
    void testGetTempMax() {
        assertEquals(284.26, weather.getTempMax(), 0.01);
    }

    @Test
    void testGetVisibility() {
        assertEquals(16093, weather.getVisibility());
    }

    @Test
    void testGetWindSpeed() {
        assertEquals(1.5, weather.getWindSpeed(), 0.01);
    }

    @Test
    void testGetWindDeg() {
        assertEquals(350, weather.getWindDeg());
    }

    @Test
    void testGetWindGust() {
        assertEquals(2.68, weather.getWindGust(), 0.01);
    }

    @Test
    void testGetCloudsAll() {
        assertEquals(1, weather.getCloudsAll());
    }

    @Test
    void testGetSysSunrise() {
        assertEquals(new Date(1560343627L * 1000), weather.getSysSunrise());
    }

    @Test
    void testGetSysSunset() {
        assertEquals(new Date(1560396563L * 1000), weather.getSysSunset());
    }
}
