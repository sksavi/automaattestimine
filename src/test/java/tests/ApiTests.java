package tests;

import builder.RepositoryBuilder;
import calculator.Calculator;
import org.junit.Before;
import org.junit.Test;
import reader.Reader;
import repository.Repository;
import validator.Validator;
import writer.Writer;

import java.io.File;
import java.io.IOException;
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
    private List<Repository> weatherRepositories = new ArrayList<>();
    private List<Repository> forecastRepositories = new ArrayList<>();


    @Before
    public void setup() throws Exception {
        //GetInput input = new GetInput();
        //String cityName = input.getCityName();

        this.reader = new Reader();
        this.writer = new Writer();
        this.validator = new Validator();
        this.calculator = new Calculator();
        RepositoryBuilder repositoryBuilder = new RepositoryBuilder(reader);

        this.weatherRepositories = repositoryBuilder.buildCurrentWeatherRepositories();
        this.forecastRepositories = repositoryBuilder.buildForecastRepositories();
    }


    @Test
    public void testAllCurrentWeatherConnectionsSuccessfulToWeatherApi() throws IOException {
        int countTrue = 0;
        for (Repository repository : weatherRepositories) {
            if (repository.currentWeatherConnection.getResponseCode() == 200) {
                countTrue++;
            }
        }
        assertEquals(weatherRepositories.size(), countTrue);
    }

    @Test
    public void testAllForecastConnectionsSuccessfulToWeatherApi() throws IOException {
        int countTrue = 0;
        for (Repository forecastRepository : forecastRepositories) {
            if (forecastRepository.forecastConnection.getResponseCode() == 200) {
                countTrue++;
            }
        }
        assertEquals(forecastRepositories.size(), countTrue);
    }

    @Test
    public void testGetCurrentTemperatureOfCity() throws IOException {
        int countTrue = 0;
        for (Repository repository : weatherRepositories) {
            String temperature = reader.readCurrentTemperatureFrom(repository.fullWeatherReport);
            boolean actual = validator.validateTemperatureFormat(temperature);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(weatherRepositories.size(), countTrue);

    }

    @Test
    public void testMaxTemperatureOfEachDayFor3DaysFromForecast() throws IOException {
        boolean actual = false;
        for (Repository forecastRepository : forecastRepositories) {
            List<String> temperatures = reader.readMaxTemperaturesFrom(forecastRepository.fullForecastReport);
            List<Double> maxTemperatures = calculator.findMaxTemperaturesFrom(temperatures);
            actual = validator.confirmMaxTemperatures(maxTemperatures, temperatures);
        }
        assertEquals(true, actual);
    }

    @Test
    public void testMinTemperatureOfEachDayFor3DaysFromForecast() throws IOException {
        boolean actual = false;
        for (Repository forecastRepository : forecastRepositories) {
            List<String> temperatures = reader.readMinTemperaturesFrom(forecastRepository.fullForecastReport);
            List<Double> minTemperatures = calculator.findMinTemperaturesFrom(temperatures);
            actual = validator.confirmMinTemperatures(minTemperatures, temperatures);
        }
        assertEquals(true, actual);
    }

    @Test
    public void testCurrentWeatherReportContainsLonAndLat() throws IOException {
        int countTrue = 0;
        for (Repository repository : weatherRepositories) {
            boolean actual = validator.validateCoordinatesExist(repository.fullWeatherReport);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(weatherRepositories.size(), countTrue);
    }

    @Test
    public void testForecastReportContainsLonAndLat() throws IOException {
        int countTrue = 0;
        for (Repository forecastRepository : forecastRepositories) {
            boolean actual = validator.validateCoordinatesExist(forecastRepository.fullForecastReport);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(forecastRepositories.size(), countTrue);
    }

    @Test
    public void testCurrentWeatherReportMayBeJsonFormat() throws IOException {
        int countTrue = 0;
        for (Repository repository : weatherRepositories) {
            boolean actual = validator.validateJsonFormat(repository.fullWeatherReport);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(weatherRepositories.size(), countTrue);
    }

    @Test
    public void testForecastReportMayBeJsonFormat() throws IOException {
        int countTrue = 0;
        for (Repository forecastRepository : forecastRepositories) {
            boolean actual = validator.validateJsonFormat(forecastRepository.fullForecastReport);
            if (actual) {
                countTrue++;
            }
        }
        assertEquals(forecastRepositories.size(), countTrue);
    }

    @Test
    public void testWriterWriteToFileMethodCalledCorrectNumberOfTimes() throws Exception {
        Writer writer = mock(Writer.class);

        for (Repository repository : weatherRepositories) {
            writer.writeReportToFile(repository);
        }
        for (Repository forecastRepository : forecastRepositories) {
            writer.writeReportToFile(forecastRepository);
        }
        int times = weatherRepositories.size() + forecastRepositories.size();

        //MyClassToTest smth = new MyClassToTest();
        //smth.doSomethingWithWriter(writer)

        verify(writer, times(3)).writeReportToFile(any());

        //when(writer.makeSOmthng()).thenReturn(3);

    }

    @Test
    public void testCorrectNumberOfFilesWrittenInOutputFolder() throws Exception {
        int count = 0;
        File folder = new File("C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\output");
        writer.cleanDirectory(folder);
        writer.writeReportsToOutputFile(weatherRepositories, forecastRepositories);
        if (folder.exists()) {
            count = folder.listFiles().length;
        } else {
            throw new IOException("Folder doesn't exist");
        }
        assertEquals(weatherRepositories.size(), count);
    }
}


