package request;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherRequest {

    private final String requestType;
    private final String cityName;
    private HttpURLConnection conCurrentWeather;
    private HttpURLConnection conForecast;

    public WeatherRequest(String cityName, String requestType) throws IOException {
        this.cityName = cityName;
        this.requestType = requestType;

        if (requestType.equals("CurrentWeather")) {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=fd09937b45795f44839aebdf691308d3");
            this.conCurrentWeather = (HttpURLConnection) url.openConnection();
        }
        if (requestType.equals("Forecast")) {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=3e50ca25365591599eb5a10eafa5c7d9");
            this.conForecast = (HttpURLConnection) url.openConnection();
        }

        if (requestType.equals("CurrentWeather") || requestType.equals("Forecast")) {
            throw new IllegalArgumentException("Request type wrong!");
        }
    }

    public String getCityName() {
        return cityName;
    }

    public HttpURLConnection getConForecast() {
        return conForecast;
    }

    public HttpURLConnection getConCurrentWeather() {
        return conCurrentWeather;
    }

    public String getRequestType() {
        return requestType;
    }
}
