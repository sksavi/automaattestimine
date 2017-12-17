package builder;

import request.WeatherRequest;

import java.io.IOException;
import java.net.HttpURLConnection;

class ConnectionBuilder {

    ConnectionBuilder() {}

    HttpURLConnection buildForecastConnection(String cityName) throws IOException {
        WeatherRequest forecastRequest = new WeatherRequest(cityName, "Forecast");
        forecastRequest.setConnection();
        return forecastRequest.getConnectionToForecast();
    }

    HttpURLConnection buildCurrentWeatherConnection(String cityName) throws IOException {
        WeatherRequest request = new WeatherRequest(cityName, "CurrentWeather");
        request.setConnection();
        return request.getConnectionToCurrentWeather();
    }
}
