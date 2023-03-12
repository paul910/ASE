package org.planner.console;

import org.planner.domain.Weather;
import org.planner.service.WeatherService;

public class WeatherConsole implements ConsoleInterface {
    private final WeatherService weatherService;

    public WeatherConsole() {
        this.weatherService = new WeatherService();
    }

    public void checkWeather() {
        String input = consoleHelper.readFromConsole("Enter city: ");
        this.weatherService.fetchWeatherByCity(input);
        if (this.weatherService.getWeather() == null) {
            consoleHelper.printError("No weather data found for city: " + input + "!");
            return;
        }
        consoleHelper.printSuccess("The current weather in " + input + " is as follows:");
        this.printInfoText();
    }

    private void printInfoText() {
        String celcius = "\u00B0C";
        Weather weather = this.weatherService.getWeather();
        consoleHelper.printBlockText("The main weather condition is " + weather.getMain() + ", which means it" + "is " + weather.getDescription() + ". The temperature is " + weather.getTemp() + "" + celcius + ", but it feels like " + weather.getFeelsLike() + "" + celcius + " due to wind and humidity. The air pressure is " + weather.getPressure() + " hPa and the humidity " + weather.getHumidity() + "%. The " + "maximum temperature will be " + weather.getTempMax() + "" + celcius + ", and the minimum " + "temperature will be " + weather.getTempMin() + "" + celcius + ". The visibility is " + weather.getVisibility() + " meters, and the wind speed is " + weather.getWindSpeed() + " m/s with a wind direction of " + weather.getWindDeg() + " degrees. Wind gusts of up to " + weather.getWindGust() + " meters per second may occur. The cloud coverage is " + weather.getCloudsAll() + "%. The sun will rise today at " + weather.getSysSunrise().getHours() + ":" + (weather.getSysSunrise().getMinutes() < 10 ? "0" + weather.getSysSunrise().getMinutes() : weather.getSysSunrise().getMinutes()) + " and set at " + weather.getSysSunset().getHours() + ":" + (weather.getSysSunset().getMinutes() < 10 ? "0" + weather.getSysSunset().getMinutes() : weather.getSysSunset().getMinutes()) + ".");
    }
}
