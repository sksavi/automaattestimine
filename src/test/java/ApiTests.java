import org.junit.Before;
import org.junit.Test;
import request.WeatherRequest;
import reader.Reader;
import writer.Writer;
import validator.Validator;
import calculator.Calculator;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ApiTests {

    private HttpURLConnection conCurrentWeather;
    private HttpURLConnection conForecast;
    private Reader reader;
    private Validator validator;
    private Writer writer;
    private Calculator calculator;
    private String requestType;
    private String requestType1;

    @Before
    public void setup() throws Exception {
        //String cityName = GetInput.getCityNameFromUser();
        //String requestType = GetInput.getRequestTypeFromUser();
        //GetInput input = new GetInput();
        //String cityName = input.getCityName();
        //String requestType = input.getRequestType();

        this.reader = new Reader();
        this.writer = new Writer();
        this.validator = new Validator();
        this.calculator = new Calculator();

        String cityName = reader.readInputTxtFile();
        String requestType = "CurrentWeather";
        String requestType1 = "Forecast";

        WeatherRequest weatherRequest = new WeatherRequest(cityName, requestType);
        WeatherRequest forecastWeatherRequest = new WeatherRequest(cityName, requestType1);

        this.requestType = weatherRequest.getRequestType();
        this.requestType1 = forecastWeatherRequest.getRequestType();


        conCurrentWeather = weatherRequest.getConCurrentWeather();
        conForecast = forecastWeatherRequest.getConForecast();
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
        String report = reader.readFrom(conCurrentWeather);
        String temperature = reader.readCurrentTemperatureFrom(report);
        boolean actual = validator.validateTemperatureFormat(temperature);
        assertEquals(true, actual);
    }

    @Test
    public void testMaxTemperatureOf3DaysFromForecast() throws IOException {
        String report = reader.readFrom(conForecast);
        List<String> temperatures = reader.readMaxTemperaturesFrom(report);
        String maxTemperature = calculator.findMaxTemperatureFrom(temperatures);
        boolean actual = validator.confirmMaxTemperature(maxTemperature, temperatures);
        assertEquals(true, actual);
    }

    @Test
    public void testMinTemperatureOf3DaysFromForecast() throws IOException {
        String report = reader.readFrom(conForecast);
        List<String> temperatures = reader.readMinTemperaturesFrom(report);
        String minTemperature = calculator.findMinTemperatureFrom(temperatures);
        boolean actual = validator.confirmMinTemperature(minTemperature, temperatures);
        assertEquals(true, actual);
    }

    @Test
    public void testCurrentWeatherGeoCoordinatesOfCityTallinn() throws IOException {
        String report = reader.readFrom(conCurrentWeather);
        boolean actual = validator.validateCoordinates(report);
        assertEquals(true, actual);
    }

    @Test
    public void testForecastGeoCoordinatesOfCityTallinn() throws IOException {
        String report = reader.readFrom(conForecast);
        boolean actual = validator.validateCoordinates(report);
        assertEquals(true, actual);
    }

    @Test
    public void testCurrentWeatherMayBeJsonFormat() throws IOException {
        String report = reader.readFrom(conCurrentWeather);
        boolean actual = validator.validateFormat(report);
        assertEquals(true, actual);
    }

    @Test
    public void testForecastMayBeJsonFormat() throws IOException {
        String report = reader.readFrom(conForecast);
        boolean actual = validator.validateFormat(report);
        assertEquals(true, actual);
    }

    @Test
    public void testWriteInfoToOutputFile() throws IOException {
        writer.writeToFile(reader, conCurrentWeather, requestType);
        writer.writeToFile(reader, conForecast, requestType1);

    }
}
