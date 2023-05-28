package org.planner.domain;

import java.util.Date;

public class WeatherBuilder {
    private String main;
    private String description;
    private double temp;
    private double feelsLike;
    private int pressure;
    private int humidity;
    private double tempMin;
    private double tempMax;
    private int visibility;
    private double windSpeed;
    private int windDeg;
    private double windGust;
    private int cloudsAll;
    private Date sysSunrise;
    private Date sysSunset;

    public WeatherBuilder setMain(String main) {
        this.main = main;
        return this;
    }

    public WeatherBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public WeatherBuilder setTemp(double temp) {
        this.temp = temp;
        return this;
    }

    public WeatherBuilder setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
        return this;
    }

    public WeatherBuilder setPressure(int pressure) {
        this.pressure = pressure;
        return this;
    }

    public WeatherBuilder setHumidity(int humidity) {
        this.humidity = humidity;
        return this;
    }

    public WeatherBuilder setTempMin(double tempMin) {
        this.tempMin = tempMin;
        return this;
    }

    public WeatherBuilder setTempMax(double tempMax) {
        this.tempMax = tempMax;
        return this;
    }

    public WeatherBuilder setVisibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    public WeatherBuilder setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public WeatherBuilder setWindDeg(int windDeg) {
        this.windDeg = windDeg;
        return this;
    }

    public WeatherBuilder setWindGust(double windGust) {
        this.windGust = windGust;
        return this;
    }

    public WeatherBuilder setCloudsAll(int cloudsAll) {
        this.cloudsAll = cloudsAll;
        return this;
    }

    public WeatherBuilder setSysSunrise(Date sysSunrise) {
        this.sysSunrise = sysSunrise;
        return this;
    }

    public WeatherBuilder setSysSunset(Date sysSunset) {
        this.sysSunset = sysSunset;
        return this;
    }

    public Weather build() {
        return new Weather(main, description, temp, feelsLike, pressure, humidity, tempMin, tempMax, visibility, windSpeed, windDeg, windGust, cloudsAll, sysSunrise, sysSunset);
    }
}
