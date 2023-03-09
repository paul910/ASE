package org.planner.domain;

import org.planner.helper.ParseHelper;

import java.util.Date;

public class Weather {
    private final ParseHelper parseHelper;

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

    public Weather(String JSONString) {
        this.parseHelper = new ParseHelper(JSONString);
        this.extractWeatherInfo();
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

    public String getInfoText() {
        String celcius = "\u00B0C";
        return "The main weather condition is " + this.main + ", which means it\n" + "is " + this.description + ". The temperature is " + this.temp + "" + celcius + ", but it \n" + "feels like " + this.feelsLike + "" + celcius + " due to wind and humidity. The \n" + "air pressure is " + this.pressure + " hPa and the humidity " + this.humidity + "%. The \n" + "maximum temperature will be " + this.tempMax + "" + celcius + ", and the minimum \n" + "temperature will be " + this.tempMin + "" + celcius + ". The visibility is " + this.visibility + "\n" + "meters, and the wind speed is " + this.windSpeed + " m/s with a wind \n" + "direction of " + this.windDeg + " degrees. Wind gusts of up to " + this.windGust + " meters per \n" + "second may occur. The cloud coverage is " + this.cloudsAll + "%. The sun \n" + "will rise today at " + this.sysSunrise.getHours() + ":" + this.sysSunrise.getMinutes() + " and set at " + this.sysSunset.getHours() + ":" + this.sysSunset.getMinutes() + ".\n";
    }

    @Override
    public String toString() {
        return "Weather{" + "city='" + city + '\'' + ", main='" + main + '\'' + ", description='" + description + '\'' + ", temp=" + temp + ", feelsLike=" + feelsLike + ", pressure=" + pressure + ", humidity=" + humidity + ", tempMin=" + tempMin + ", tempMax=" + tempMax + ", visibility=" + visibility + ", windSpeed=" + windSpeed + ", windDeg=" + windDeg + ", windGust=" + windGust + ", cloudsAll=" + cloudsAll + ", sysSunrise=" + sysSunrise + ", sysSunset=" + sysSunset + '}';
    }

    private void extractWeatherInfo() {
        this.city = this.parseHelper.getValueByRegex("name");
        this.main = this.parseHelper.getValueByRegex("main");
        this.description = this.parseHelper.getValueByRegex("description");
        this.temp = this.parseHelper.getDoubleValueByRegex("temp");
        this.feelsLike = this.parseHelper.getDoubleValueByRegex("feels_like");
        this.pressure = this.parseHelper.getIntegerValueByRegex("pressure");
        this.humidity = this.parseHelper.getIntegerValueByRegex("humidity");
        this.tempMin = this.parseHelper.getDoubleValueByRegex("temp_min");
        this.tempMax = this.parseHelper.getDoubleValueByRegex("temp_max");
        this.visibility = this.parseHelper.getIntegerValueByRegex("visibility");
        this.windSpeed = this.parseHelper.getDoubleValueByRegex("speed");
        this.windDeg = this.parseHelper.getIntegerValueByRegex("deg");
        this.windGust = this.parseHelper.getDoubleValueByRegex("gust");
        this.cloudsAll = this.parseHelper.getIntegerValueByRegex("all");
        this.sysSunrise = this.parseHelper.getDateValueByRegex("sunrise");
        this.sysSunset = this.parseHelper.getDateValueByRegex("sunset");
    }
}