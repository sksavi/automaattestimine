package builder;

import calculator.Calculator;
import reader.Reader;
import repository.Repository;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class RepositoryBuilder {

    private Reader reader;
    private ConnectionBuilder connectionBuilder;
    private List<Repository> repositories = new ArrayList<>();
    private List<Repository> forecastRepositories = new ArrayList<>();
    private HttpURLConnection currentWeatherConnection;
    private HttpURLConnection forecastConnection;


    public RepositoryBuilder(Reader reader) {
        this.reader = reader;
        this.connectionBuilder = new ConnectionBuilder();
    }

    public List<Repository> buildCurrentWeatherRepositories() throws IOException {
        String cities = reader.readInputTxtFile();
        String[] city = cities.split(", ");

        for (String aCity : city) {
            Repository repository = new Repository(aCity);
            repository.setCityName(aCity);
            repository.setRequestType("CurrentWeather");

            this.currentWeatherConnection = connectionBuilder.buildCurrentWeatherConnection(aCity);
            setInfoToRepository(repository);
            repositories.add(repository);
        }
        return repositories;
    }

    public List<Repository> buildForecastRepositories() throws IOException {
        String cities = reader.readInputTxtFile();
        String[] city = cities.split(", ");

        for (String aCity : city) {
            Repository repository = new Repository(aCity);
            repository.setCityName(aCity);
            repository.setRequestType("Forecast");

            this.forecastConnection = connectionBuilder.buildForecastConnection(aCity);
            setInfoToRepository(repository);
            forecastRepositories.add(repository);
        }
        return forecastRepositories;
    }

    private void setInfoToRepository(Repository repository) throws IOException {
        Calculator calculator = new Calculator();

        if (repository.requestType.equals("CurrentWeather")) {

            String weatherReport = reader.readFrom(currentWeatherConnection);
            repository.setFullWeatherReport(weatherReport);
            repository.setCoordinates(reader.readCoordinatesFrom(weatherReport, repository.requestType));
            repository.setCurrentTemperature(reader.readCurrentTemperatureFrom(weatherReport));
            repository.setCurrentWeatherConnection(currentWeatherConnection);

        } else {

            String forecastReport = reader.readFrom(forecastConnection);
            repository.setFullForecastReport(forecastReport);
            repository.setCurrentTemperature(reader.readCurrentTemperatureFrom(forecastReport));
            repository.setCoordinates(reader.readCoordinatesFrom(forecastReport, repository.requestType));
            repository.setForecastConnection(forecastConnection);

            List<String> minTemperatures = reader.readMinTemperaturesFrom(forecastReport);
            List<Double> _3minTemperatures = calculator.findMinTemperaturesFrom(minTemperatures);

            repository._3minTemperatures.addAll(_3minTemperatures);

            List<String> maxTemperatures = reader.readMaxTemperaturesFrom(forecastReport);
            List<Double> _3maxTemperatures = calculator.findMaxTemperaturesFrom(maxTemperatures);
            repository._3maxTemperatures.addAll(_3maxTemperatures);
        }
        repository.setReport(buildReport(repository));
    }

    private String buildReport(Repository repository) {
        String coords = repository.coordinates;
        coords = coords.substring(1, coords.length() - 1);
        String[] coord = coords.split(",");

        if (repository.requestType.equals("CurrentWeather")) {

            return "{\n" +
                    "    \"city\":{\n" +
                    "        \"name\":\"" + repository.cityName + "\",\n" +
                    "        \"coord\":{\n" +
                    "            " + coord[0] + ",\n" +
                    "            " + coord[1] + "\n" +
                    "        },\n" +
                    "       \"temp_now\":" + repository.currentTemperature + "\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        } else {

            return "{\n" +
                    "    \"city\":{\n" +
                    "        \"name\":\"" + repository.cityName + "\",\n" +
                    "        \"coord\":{\n" +
                    "            " + coord[0] + ",\n" +
                    "            " + coord[1] + "\n" +
                    "        },\n" +
                    "        \"temperatures\":{\n" +
                    "            \"temp_now\":" + repository.currentTemperature + ",\n" +
                    "            \"temp_min_today\":" + repository._3minTemperatures.get(0) + ",\n" +
                    "            \"temp_min_tomorrow\":" + repository._3minTemperatures.get(1) + ",\n" +
                    "            \"temp_min_after_tomorrow\":" + repository._3minTemperatures.get(2) + ",\n" +
                    "            \"temp_max_today\":" + repository._3maxTemperatures.get(0) + ",\n" +
                    "            \"temp_max_after_tomorrow\":" + repository._3maxTemperatures.get(1) + ",\n" +
                    "            \"temp_max_after_tomorrow\":" + repository._3maxTemperatures.get(2) + "\n" +
                    "        }\n" +
                    "    }\n" +
                    "}";
        }
    }
}
