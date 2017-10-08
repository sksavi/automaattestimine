import calculator.Calculator;
import connection.HttpUtility;
import json.JsonFromFile;
import org.junit.Before;
import org.junit.Test;
import search.GetInfoType;
import validator.Validator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ApiTests {


    private String data;

    @Before
    public void setup() throws Exception {
        this.data = JsonFromFile.read();
    }
    
    @Test
    public void testHttpConnectionToWeatherApi() {
        String requestUrl = "<API REQUEST URL>";
        String response = HttpUtility.makeHttpGetRequest(requestUrl);

        assertEquals("correctUrl", response);
    }

    @Test
    public void testHttpSuccessfulConnectionToWeatherApi() throws IOException {
        URL url = new URL("<API REQUEST URL>");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        assertEquals(conn.getResponseCode(), 200);
    }

    @Test
    public void testGetCurrentTemperatureOfTallinn() {
        String temperature = GetInfoType.temperature(data);
        boolean actual = Validator.validateTemperatureFormat(temperature);
        assertEquals(true, actual);
    }

    @Test
    public void testMaxTemperatureOf3Days() {
        List<String> temperatures = GetInfoType.temperatures(data);
        String maxTemperature = Calculator.findMaxTemperature(temperatures);
        boolean actual = Validator.confirmMaxTemperature(maxTemperature, temperatures);
        assertEquals(true, actual);
    }

    @Test
    public void testMinTemperatureOf3Days() {
        List<String> temperatures = GetInfoType.temperatures(data);
        String minTemperature = Calculator.findMaxTemperature(temperatures);
        boolean actual = Validator.confirmMinTemperature(minTemperature, temperatures);
        assertEquals(true, actual);
    }

    @Test
    public void testGeoCoordinatesOfCityTallinn() {
        boolean actual = Validator.validateCoord(data);
        assertEquals(true, actual);
    }

    @Test
    public void testMayBeJsonFormat() {
        boolean actual = Validator.validateFormat(data);
        assertEquals(true, actual);
    }
}
