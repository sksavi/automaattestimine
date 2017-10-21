package weatherrequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherRequest {

    private HttpURLConnection conCurrentWeather;
    private HttpURLConnection conForecast;
    private String cityName;

    public WeatherRequest(String cityName, String requestType) throws IOException {
        this.cityName = cityName;
        System.out.println(requestType.equals("CurrentWeather"));
        if (requestType.equals("CurrentWeather")) {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&APPID=fd09937b45795f44839aebdf691308d3");
            this.conCurrentWeather = (HttpURLConnection) url.openConnection();
        }
        if (requestType.equals("Forecast")) {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=3e50ca25365591599eb5a10eafa5c7d9");
            this.conForecast = (HttpURLConnection) url.openConnection();
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
}
