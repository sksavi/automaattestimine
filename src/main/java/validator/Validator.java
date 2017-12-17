package validator;

import calculator.Calculator;

import java.util.List;

public class Validator {

    public Validator() {}

    public boolean validateJsonFormat(String data) {
        return data.startsWith("{") && data.endsWith("}");
    }

    public boolean validateCoordinatesExist(String fullReport) {
        return fullReport.contains("{\"lon\":") && fullReport.contains("\"lat\":") ||
               fullReport.contains("{\"lat\":") && fullReport.contains("\"lon\":");
    }

    public boolean validateTemperatureFormat(String temperature) {
        for (int i = 0; i < temperature.length(); i++) {
            if (!Character.isDigit(temperature.charAt(i))) {
                if (i == 3) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public boolean confirmMaxTemperatures(List<Double> maxTemperatures, List<String> temperatures) {
        Calculator calculator = new Calculator();

        if (calculator.findHighestTempFrom(temperatures, 0, 8) == maxTemperatures.get(0)) {
            if (calculator.findHighestTempFrom(temperatures, 8, 16) == maxTemperatures.get(1)) {
                if (calculator.findHighestTempFrom(temperatures, 16, 24) == maxTemperatures.get(2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean confirmMinTemperatures(List<Double> minTemperatures, List<String> temperatures) {
        Calculator calculator = new Calculator();

        if (calculator.findLowestTempFrom(temperatures, 0, 8) == minTemperatures.get(0)) {
            if (calculator.findLowestTempFrom(temperatures, 8, 16) == minTemperatures.get(1)) {
                if (calculator.findLowestTempFrom(temperatures, 16, 24) == minTemperatures.get(2)) {
                    return true;
                }
            }
        }
        return true;
    }

}