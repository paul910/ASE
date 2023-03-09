package org.planner.domain;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Weather {
    private String city;
    private String JSONString;

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
        this.JSONString = JSONString;
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
        return "The main weather condition is " + this.main + ", which means it\n"
                + "is " + this.description + ". The temperature is " + this.temp + "" + celcius
                + ", but it \n"
                + "feels like " + this.feelsLike + "" + celcius + " due to wind and humidity. The \n"
                + "air pressure is " + this.pressure + " hPa and the humidity " + this.humidity + "%. The \n"
                + "maximum temperature will be " + this.tempMax + "" + celcius + ", and the minimum \n"
                + "temperature will be " + this.tempMin + "" + celcius + ". The visibility is " + this.visibility
                + "\n"
                + "meters, and the wind speed is " + this.windSpeed + " m/s with a wind \n"
                + "direction of " + this.windDeg + " degrees. Wind gusts of up to " + this.windGust + " meters per \n"
                + "second may occur. The cloud coverage is " + this.cloudsAll + "%. The sun \n"
                + "will rise today at " + this.sysSunrise.getHours() + ":" + this.sysSunrise.getMinutes()
                + " and set at " + this.sysSunset.getHours() + ":" + this.sysSunset.getMinutes() + ".\n";
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", JSONString='" + JSONString + '\'' +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", visibility=" + visibility +
                ", windSpeed=" + windSpeed +
                ", windDeg=" + windDeg +
                ", windGust=" + windGust +
                ", cloudsAll=" + cloudsAll +
                ", sysSunrise=" + sysSunrise +
                ", sysSunset=" + sysSunset +
                '}';
    }

    private void extractWeatherInfo() {
        this.city = this.getValueByRegex("name");
        this.main = this.getValueByRegex("main");
        this.description = this.getValueByRegex("description");
        this.temp = this.getDoubleValueByRegex("temp");
        this.feelsLike = this.getDoubleValueByRegex("feels_like");
        this.pressure = this.getIntegerValueByRegex("pressure");
        this.humidity = this.getIntegerValueByRegex("humidity");
        this.tempMin = this.getDoubleValueByRegex("temp_min");
        this.tempMax = this.getDoubleValueByRegex("temp_max");
        this.visibility = this.getIntegerValueByRegex("visibility");
        this.windSpeed = this.getDoubleValueByRegex("speed");
        this.windDeg = this.getIntegerValueByRegex("deg");
        this.windGust = this.getDoubleValueByRegex("gust");
        this.cloudsAll = this.getIntegerValueByRegex("all");
        this.sysSunrise = this.getDateValueByRegex("sunrise");
        this.sysSunset = this.getDateValueByRegex("sunset");
    }

    private String getValueByRegex(String value) {
        Matcher matcher = regexMatcher(value);
        if (matcher.find()) {
            return matcher.group(1).replace("\"", "");
        }
        return "";
    }

    private Double getDoubleValueByRegex(String value) {
        Matcher matcher = regexMatcher(value);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return 0.0;
    }

    private Integer getIntegerValueByRegex(String value) {
        Matcher matcher = regexMatcher(value);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }

    private Date getDateValueByRegex(String value) {
        Matcher matcher = regexMatcher(value);
        if (matcher.find()) {
            return new Date(Long.parseLong(matcher.group(1)) * 1000);
        }
        return new Date();
    }

    private Matcher regexMatcher(String value) {
        Pattern pattern = Pattern.compile("\"" + value + "\"\\s*:\\s*((\"([^\"]*)\")|((\\d+(?:\\.\\d+)?)))");
        Matcher matcher = pattern.matcher(this.JSONString);
        return matcher;
    }
}