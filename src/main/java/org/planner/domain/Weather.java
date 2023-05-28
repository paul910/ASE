package org.planner.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Weather {
    // Weather
    private final String main;
    private final String description;

    // main.Main
    private final double temp;
    private final double feelsLike;
    private final int pressure;
    private final int humidity;
    private final double tempMin;
    private final double tempMax;

    // Visibility
    private final int visibility;

    // Wind
    private final double windSpeed;
    private final int windDeg;
    private final double windGust;

    // Clouds
    private final int cloudsAll;

    // Sys
    private final Date sysSunrise;
    private final Date sysSunset;

    protected Weather(String main, String description, double temp, double feelsLike, int pressure, int humidity, double tempMin, double tempMax, int visibility, double windSpeed, int windDeg, double windGust, int cloudsAll, Date sysSunrise, Date sysSunset) {
        this.main = main;
        this.description = description;
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.visibility = visibility;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
        this.windGust = windGust;
        this.cloudsAll = cloudsAll;
        this.sysSunrise = sysSunrise;
        this.sysSunset = sysSunset;
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