package org.planner.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Weather {
    private String city;
    // Weather
    private String main;
    private String description;

    // main.Main
    private double temp;
    private double feelsLike;
    private int pressure;
    private int humidity;
    private double tempMin;
    private double tempMax;

    // Visibility
    private int visibility;

    // Wind
    private double windSpeed;
    private int windDeg;
    private double windGust;

    // Clouds
    private int cloudsAll;

    // Sys
    private Date sysSunrise;
    private Date sysSunset;

    public Weather(Map<String, Object> obj) {
        Map<String, Object> weather = (Map<String, Object>) ((List<Object>) obj.get("weather")).get(0);
        {
            this.main = (String) weather.get("main");
            this.description = (String) weather.get("description");
        }
        Map<String, Object> main = (Map<String, Object>) obj.get("main");
        {
            this.temp = (double) main.get("temp");
            this.feelsLike = (double) main.get("feels_like");
            this.pressure = Long.valueOf((long) main.get("pressure")).intValue();
            this.humidity = Long.valueOf((long) main.get("humidity")).intValue();
            this.tempMin = (double) main.get("temp_min");
            this.tempMax = (double) main.get("temp_max");
        }
        this.visibility = Long.valueOf((long) obj.get("visibility")).intValue();
        Map<String, Object> wind = (Map<String, Object>) obj.get("wind");
        {
            this.windSpeed = (double) (wind.get("speed") != null ? wind.get("speed") : 0.0);
            this.windDeg = Long.valueOf((long) wind.get("deg")).intValue();
            this.windGust = (double) (wind.get("gust") != null ? wind.get("gust") : 0.0);
        }
        Map<String, Object> clouds = (Map<String, Object>) obj.get("clouds");
        {
            this.cloudsAll = Long.valueOf((long) clouds.get("all")).intValue();
        }
        Map<String, Object> sys = (Map<String, Object>) obj.get("sys");
        {
            this.sysSunrise = new Date((long) sys.get("sunrise") * 1000);
            this.sysSunset = new Date((long) sys.get("sunset") * 1000);
        }
    }

    public String getCity() {
        return city;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public double getTemp() {
        return temp;
    }

    public double getFeelsLike() {
        return feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public int getVisibility() {
        return visibility;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }

    public double getWindGust() {
        return windGust;
    }

    public int getCloudsAll() {
        return cloudsAll;
    }

    public Date getSysSunrise() {
        return sysSunrise;
    }

    public Date getSysSunset() {
        return sysSunset;
    }
}