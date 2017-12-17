package repository;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    public String cityName;
    public String report;
    public String fullWeatherReport;
    public String fullForecastReport;
    public String currentTemperature;
    public String coordinates;
    public String requestType;
    public HttpURLConnection currentWeatherConnection;
    public HttpURLConnection forecastConnection;
    public List<Double> _3maxTemperatures = new ArrayList<>();
    public List<Double> _3minTemperatures = new ArrayList<>();


    public Repository(String cityName) {
        this.cityName = cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public void setFullWeatherReport(String fullWeatherReport) {
        this.fullWeatherReport = fullWeatherReport;
    }

    public void setFullForecastReport(String fullForecastReport) {
        this.fullForecastReport = fullForecastReport;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void setCurrentWeatherConnection(HttpURLConnection currentWeatherConnection) {
        this.currentWeatherConnection = currentWeatherConnection;
    }

    public void setForecastConnection(HttpURLConnection forecastConnection) {
        this.forecastConnection = forecastConnection;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getCityName() {
        return cityName;
    }

    public String getReport() {
        return report;
    }

    public String getFullWeatherReport() {
        return fullWeatherReport;
    }

    public String getFullForecastReport() {
        return fullForecastReport;
    }

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getRequestType() {
        return requestType;
    }

    public HttpURLConnection getCurrentWeatherConnection() {
        return currentWeatherConnection;
    }

    public HttpURLConnection getForecastConnection() {
        return forecastConnection;
    }

    public List<Double> get_3maxTemperatures() {
        return _3maxTemperatures;
    }

    public List<Double> get_3minTemperatures() {
        return _3minTemperatures;
    }

}
