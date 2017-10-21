import calculator.Calculator;
import org.junit.Before;
import org.junit.Test;
import search.GetInfoType;
import search.ReadResponse;
import validator.Validator;
import weatherrequest.WeatherRequest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ApiTests {

    private HttpURLConnection conCurrentWeather;
    private HttpURLConnection conForecast;

    @Before
    public void setup() throws Exception {
        //String cityName = GetInput.getCityNameFromUser();
        //String requestType = GetInput.getRequestTypeFromUser();
        String cityName = "Tallinn";
        String requestType = "CurrentWeather";
        String requestType1 = "Forecast";

        WeatherRequest weatherRequest = new WeatherRequest(cityName, requestType);
        WeatherRequest weatherRequestForecast = new WeatherRequest(cityName, requestType1);

        conCurrentWeather = weatherRequest.getConCurrentWeather();
        conForecast = weatherRequestForecast.getConForecast();
    }

    @Test
    public void testHttpSuccessfulConnectionToWeatherApiCurrentWeather() throws IOException {
        assertEquals(conCurrentWeather.getResponseCode(), 200);
    }

    @Test
    public void testHttpSuccessfulConnectionToWeatherApiForecast() throws IOException {
        assertEquals(conForecast.getResponseCode(), 200);
    }

    @Test
    public void testGetCurrentTemperatureOfTallinn() throws IOException {
        String data = ReadResponse.readFrom(conCurrentWeather);
        String temperature = GetInfoType.temperature(data);
        boolean actual = Validator.validateTemperatureFormat(temperature);
        assertEquals(true, actual);
    }

    @Test
    public void testMaxTemperatureOf3DaysFromForecast() throws IOException {
        String data = ReadResponse.readFrom(conForecast);
        List<String> temperatures = GetInfoType.maxTemperatures(data);
        String maxTemperature = Calculator.findMaxTemperature(temperatures);
        boolean actual = Validator.confirmMaxTemperature(maxTemperature, temperatures);
        assertEquals(true, actual);
    }

    @Test
    public void testMinTemperatureOf3DaysFromForecast() throws IOException {
        String data = ReadResponse.readFrom(conForecast);
        List<String> temperatures = GetInfoType.minTemperatures(data);
        String minTemperature = Calculator.findMinTemperature(temperatures);
        boolean actual = Validator.confirmMinTemperature(minTemperature, temperatures);
        assertEquals(true, actual);
    }

    @Test
    public void testCurrentWeatherGeoCoordinatesOfCityTallinn() throws IOException {
        String data = ReadResponse.readFrom(conCurrentWeather);
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
        String data = ReadResponse.readFrom(conCurrentWeather);
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
