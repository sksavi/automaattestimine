package request;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherRequest {

    private final String requestType;
    private final String cityName;
    private HttpURLConnection connectionToCurrentWeather;
    private HttpURLConnection connectionToForecast;

    public WeatherRequest(String cityName, String requestType) {
        this.cityName = cityName;
        this.requestType = requestType;
    }

    public void setConnection() throws IOException {
        if (requestType.equals("CurrentWeather")) {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=fd09937b45795f44839aebdf691308d3");
            this.connectionToCurrentWeather = (HttpURLConnection) url.openConnection();
        } else if (requestType.equals("Forecast")) {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=3e50ca25365591599eb5a10eafa5c7d9");
            this.connectionToForecast = (HttpURLConnection) url.openConnection();
        } else {
            throw new IllegalArgumentException("Wrong request type!");
        }
    }
    public String getCityName() {
        return cityName;
    }

    public HttpURLConnection getConnectionToForecast() {
        return connectionToForecast;
    }

    public HttpURLConnection getConnectionToCurrentWeather() {
        return connectionToCurrentWeather;
    }

    public String getRequestType() {
        return requestType;
    }
}
