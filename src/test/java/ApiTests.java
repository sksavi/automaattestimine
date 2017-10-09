import org.junit.Before;
import org.junit.Test;
import search.GetInfoType;
import search.ReadResponse;
import validator.Validator;
import calculator.Calculator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ApiTests {

    private HttpURLConnection conCurrentWheather;
    private HttpURLConnection conForecast;

    @Before
    public void setup() throws Exception {
        String requestCurrentWeatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=tallinn&APPID=fd09937b45795f44839aebdf691308d3";
        String requestForecastUrl = "http://api.openweathermap.org/data/2.5/forecast?id=588409&appid=3e50ca25365591599eb5a10eafa5c7d9";
        URL url1 = new URL(requestCurrentWeatherUrl);
        URL url2 = new URL(requestForecastUrl);
        this.conCurrentWheather = (HttpURLConnection) url1.openConnection();
        this.conForecast = (HttpURLConnection) url2.openConnection();
    }

    @Test
    public void testHttpSuccessfulConnectionToWeatherApiCurrentWeather() throws IOException {
        assertEquals(conCurrentWheather.getResponseCode(), 200);
    }

    @Test
    public void testHttpSuccessfulConnectionToWeatherApiForecast() throws IOException {
        assertEquals(conForecast.getResponseCode(), 200);
    }

    @Test
    public void testGetCurrentTemperatureOfTallinn() throws IOException {
        String data = ReadResponse.readFrom(conCurrentWheather);
        String temperature = GetInfoType.temperature(data);
        boolean actual = Validator.validateTemperatureFormat(temperature);
        assertEquals(true, actual);
    }

    @Test
    public void testMaxTemperatureOf3Days() throws IOException {
        String data = ReadResponse.readFrom(conForecast);
        List<String> temperatures = GetInfoType.temperatures(data);
        String maxTemperature = Calculator.findMaxTemperature(temperatures);
        boolean actual = Validator.confirmMaxTemperature(maxTemperature, temperatures);
        assertEquals(true, actual);
    }

    @Test
    public void testMinTemperatureOf3Days() throws IOException {
        String data = ReadResponse.readFrom(conForecast);
        List<String> temperatures = GetInfoType.temperatures(data);
        String minTemperature = Calculator.findMinTemperature(temperatures);
        boolean actual = Validator.confirmMinTemperature(minTemperature, temperatures);
        assertEquals(true, actual);
    }

    @Test
    public void testCurrentWeatherGeoCoordinatesOfCityTallinn() throws IOException {
        String data = ReadResponse.readFrom(conCurrentWheather);
        boolean actual = Validator.validateCoord(data);
        assertEquals(true, actual);
    }

    @Test
    public void testForecastGeoCoordinatesOfCityTallinn() throws IOException {
        String data = ReadResponse.readFrom(conForecast);
        boolean actual = Validator.validateCoord(data);
        assertEquals(true, actual);
    }

    @Test
    public void testCurrentWeatherMayBeJsonFormat() throws IOException {
        String data = ReadResponse.readFrom(conCurrentWheather);
        boolean actual = Validator.validateFormat(data);
        assertEquals(true, actual);
    }

    @Test
    public void testForecastMayBeJsonFormat() throws IOException {
        String data = ReadResponse.readFrom(conForecast);
        boolean actual = Validator.validateFormat(data);
        assertEquals(true, actual);
    }
}
