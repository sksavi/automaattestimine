package tests;

import builder.RepositoryBuilder;
import calculator.Calculator;
import org.junit.Test;
import reader.Reader;
import repository.Repository;
import writer.Writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MockTest {


    @Test
    public void testOutputFileWrittenCorrectly() throws IOException {
        Repository repository = mock(Repository.class);
        Reader reader = new Reader();
        Calculator calculator = new Calculator();


        String fullReport = reader.readFullReportSample();

        when(repository.getFullForecastReport()).thenReturn(fullReport);
        when(repository.getCityName()).thenReturn(reader.readNameFrom(fullReport, "Forecast"));
        when(repository.getCoordinates()).thenReturn(reader.readCoordinatesFrom(fullReport, "Forecast"));
        when(repository.getCurrentTemperature()).thenReturn(reader.readCurrentTemperatureFrom(fullReport));

        List<String> maxTemps = reader.readMaxTemperaturesFrom(fullReport);
        List<String> minTemps = reader.readMaxTemperaturesFrom(fullReport);

        when(repository.get_3maxTemperatures()).thenReturn(calculator.findMaxTemperaturesFrom(maxTemps));
        when(repository.get_3minTemperatures()).thenReturn(calculator.findMinTemperaturesFrom(minTemps));


        String coords = repository.getCoordinates();

        coords = coords.substring(1, coords.length() - 1);
        String[] coord = coords.split(",");

        String expectedReport = reader.readOutputSample();

        String actualReport =
                        "{" +
                        "    \"city\":{" +
                        "        \"name\":\"" + repository.getCityName() + "\"," +
                        "        \"coord\":{" +
                        "            " + coord[0] + "," +
                        "            " + coord[1] + "" +
                        "        }," +
                        "        \"temperatures\":{" +
                        "            \"temp_now\":" + repository.getCurrentTemperature() + "," +
                        "            \"temp_min_today\":" + repository.get_3minTemperatures().get(0) + "," +
                        "            \"temp_min_tomorrow\":" + repository.get_3minTemperatures().get(1) + "," +
                        "            \"temp_min_after_tomorrow\":" + repository.get_3minTemperatures().get(2) + "," +
                        "            \"temp_max_today\":" + repository.get_3maxTemperatures().get(0) + "," +
                        "            \"temp_max_after_tomorrow\":" + repository.get_3maxTemperatures().get(1) + "," +
                        "            \"temp_max_after_tomorrow\":" + repository.get_3maxTemperatures().get(2) +
                        "        }" +
                        "    }" +
                        "}";

        assertEquals(expectedReport, actualReport);
    }

    @Test
    public void testIfWriterWritesFileWithCorrectName() throws Exception {
        Repository repository = mock(Repository.class);
        Writer writer = new Writer();
        Reader reader = new Reader();

        when(repository.getCityName()).thenReturn("MockedCity");
        when(repository.getReport()).thenReturn("This file is for testing");
        writer.writeReportToFile(repository);
        boolean actual = reader.findMatchInFolder(repository);

        assertEquals(true, actual);
    }


    @Test
    public void testMockedReaderDoSomething() throws Exception {
        Reader reader = mock(Reader.class);

        when(reader.readInputTxtFile()).thenReturn("Linn1, Linn2");

        RepositoryBuilder repositoryBuilder = new RepositoryBuilder(reader);

        Repository linn1 = new Repository("Linn1");
        Repository linn2 = new Repository("Linn2");

        List<Repository> repositories = new ArrayList<>();
        repositories.add(linn1);
        repositories.add(linn2);

        when(repositoryBuilder.buildCurrentWeatherRepositories()).thenReturn(repositories);

        //......
    }

}
