package tests;

import calculator.Calculator;
import org.junit.Before;
import org.junit.Test;
import reader.Reader;
import repository.WeatherRepository;
import request.WeatherRequest;
import validator.Validator;
import writer.Writer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ApiTests {

    private Reader reader;
    private Validator validator;
    private Writer writer;
    private Calculator calculator;
    private WeatherRepository weatherRepository;
    private String requestTypeCurrentWeather;
    private String requestTypeForecast;
    private List<HttpURLConnection> currentWeatherConnections = new ArrayList<>();
    private List<HttpURLConnection> forecastConnections = new ArrayList<>();


    @Before
    public void setup() throws Exception {
        //String cityName = GetInput();
        //String requestType = GetInput.getRequestTypeFromUser();
        //GetInput input = new GetInput();
        //String cityName = input.getCityName();
        //String requestType = input.getRequestType();

        this.reader = new Reader();
        this.writer = new Writer();
        this.validator = new Validator();
        this.calculator = new Calculator();
        //this.weatherRepository = new WeatherRepository();

        this.requestTypeCurrentWeather = "CurrentWeather";
        this.requestTypeForecast = "Forecast";

        makeListOfConnectionsToMakeRequestsToAPI();

    }

    private void makeListOfConnectionsToMakeRequestsToAPI() throws IOException {
        String cities = reader.readInputTxtFile();
        String[] city = cities.split(", ");
        for (int i = 0; i < city.length; i++) {
            WeatherRequest request = new WeatherRequest(city[i], requestTypeCurrentWeather);
            WeatherRequest forecastRequest = new WeatherRequest(city[i], requestTypeForecast);

            request.setConnection();
            forecastRequest.setConnection();

            HttpURLConnection currentWeatherConnection = request.getConCurrentWeather();
            HttpURLConnection forecastConnection = forecastRequest.getConForecast();
            currentWeatherConnections.add(currentWeatherConnection);
            forecastConnections.add(forecastConnection);
        }
    }


    @Test
    public void testAllCurrentWeatherConnectionsSuccessfulToWeatherApi() throws IOException {
        int countTrue = 0;
        for (int i = 0; i < currentWeatherConnections.size(); i++) {
            if (currentWeatherConnections.get(i).getResponseCode() == 200) {
                countTrue++;
            }
        }
        assertEquals(currentWeatherConnections.size(), countTrue);
    }

    @Test
    public void testAllForecastConnectionsSuccessfulToWeatherApi() throws IOException {
        int countTrue = 0;
        for (int i = 0; i < forecastConnections.size(); i++) {
            if (forecastConnections.get(i).getResponseCode() == 200) {
                countTrue++;
            }
        }
        assertEquals(forecastConnections.size(), countTrue);
    }

    @Test
    public void testGetCurrentTemperatureOfCity() throws IOException {
        int countTrue = 0;
        for (int i = 0; i < currentWeatherConnections.size(); i++) {
            String report = reader.readFrom(currentWeatherConnections.get(i));
            String temperature = reader.readCurrentTemperatureFrom(report);
            boolean actual = validator.validateTemperatureFormat(temperature);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(currentWeatherConnections.size(), countTrue);

    }

    @Test
    public void testMaxTemperatureOf3DaysFromForecast() throws IOException {
        int countTrue = 0;
        for (int i = 0; i < forecastConnections.size(); i++) {
            String report = reader.readFrom(forecastConnections.get(i));
            List<String> temperatures = reader.readMaxTemperaturesFrom(report);
            String maxTemperature = calculator.findMaxTemperatureFrom(temperatures);
            boolean actual = validator.confirmMaxTemperature(maxTemperature, temperatures);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(forecastConnections.size(), countTrue);
    }

    @Test
    public void testMinTemperatureOf3DaysFromForecast() throws IOException {
        int countTrue = 0;
        for (int i = 0; i < forecastConnections.size(); i++) {
            String report = reader.readFrom(forecastConnections.get(i));
            List<String> temperatures = reader.readMinTemperaturesFrom(report);
            String minTemperature = calculator.findMinTemperatureFrom(temperatures);
            boolean actual = validator.confirmMinTemperature(minTemperature, temperatures);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(forecastConnections.size(), countTrue);
    }

    @Test
    public void testCurrentWeatherReportContainsLonAndLat() throws IOException {
        int countTrue = 0;
        for (int i = 0; i < currentWeatherConnections.size(); i++) {
            String report = reader.readFrom(currentWeatherConnections.get(i));
            boolean actual = validator.validateCoordinatesExist(report);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(currentWeatherConnections.size(), countTrue);
    }

    @Test
    public void testForcecastReportContainsLonAndLat() throws IOException {
        int countTrue = 0;
        for (int i = 0; i < forecastConnections.size(); i++) {
            String report = reader.readFrom(forecastConnections.get(i));
            boolean actual = validator.validateCoordinatesExist(report);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(forecastConnections.size(), countTrue);
    }

    @Test
    public void testCurrentWeatherReportMayBeJsonFormat() throws IOException {
        int countTrue = 0;
        for (int i = 0; i < currentWeatherConnections.size(); i++) {
            String report = reader.readFrom(currentWeatherConnections.get(i));
            boolean actual = validator.validateFormat(report);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(currentWeatherConnections.size(), countTrue);
    }

    @Test
    public void testForecastReportMayBeJsonFormat() throws IOException {
        int countTrue = 0;
        for (int i = 0; i < forecastConnections.size(); i++) {
            String report = reader.readFrom(forecastConnections.get(i));
            boolean actual = validator.validateFormat(report);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(currentWeatherConnections.size(), countTrue);
    }

    @Test
    public void testWriteReportsToOutputFile() throws IOException {
        for (int i = 0; i < currentWeatherConnections.size(); i++) {
            writer.writeToFileBETTER();
            writer.writeToFile(reader, currentWeatherConnections.get(i), requestTypeCurrentWeather);
            writer.writeToFile(reader, forecastConnections.get(i), requestTypeForecast);
        }

    }


    @Test
    public void testWriterWriteToFileMethodCalledCorrectNumberOfTimes() throws IOException {
        Writer writer = mock(Writer.class);

        for (int i = 0; i < currentWeatherConnections.size(); i++) {
            writer.writeToFile(reader, currentWeatherConnections.get(i), requestTypeCurrentWeather);
            writer.writeToFile(reader, forecastConnections.get(i), requestTypeForecast);
        }
        verify(writer, times(6)).writeToFile(any(), any(), anyString());
    }
}


