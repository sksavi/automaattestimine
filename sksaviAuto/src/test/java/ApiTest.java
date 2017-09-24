import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.assertEquals;


public class ApiTest {

    @Test
    public void testHttpConnectionToExampleApi() {
        String requestUrl = "<API REQUEST URL>";
        String response = HttpUtility.makeHttpGetRequest(requestUrl);

        assertEquals("correctUrl", response);
    }

    @Test
    public void testHttpSuccessfulConnectionToExampleApi() throws IOException {
        URL url = new URL("<API REQUEST URL>");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        assertEquals(conn.getResponseCode(), 200);
    }

    @Test
    public void testCurrentTemperature() {
    }

    @Test
    public void testFindMaxTemperature() {
    }

    @Test
    public void testFindMinTemperature() {
    }

    @Test
    public void testForecastEveryThreeHour() {
    }

    @Test
    public void testCoordinatesAreInCorrectFormat() {
    }

    @Test
    public void testIfFormatIsJSON() {
    }




}
