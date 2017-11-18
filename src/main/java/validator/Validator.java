package validator;

import java.util.List;

public class Validator {

    public Validator() {}

    public boolean validateFormat(String data) {
        return data.startsWith("{") && data.endsWith("}");
    }

    public boolean validateCoordinatesExist(String report) {
        return report.contains("{\"lon\":") && report.contains("\"lat\":");
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

    public boolean confirmMaxTemperature(String maxTemperature, List<String> temperatures) {
        for (int i = 0; i < temperatures.size() ; i++) {
            if (Double.valueOf(maxTemperature) < Double.valueOf(temperatures.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean confirmMinTemperature(String minTemperature, List<String> temperatures) {
        for (int i = 0; i < temperatures.size() ; i++) {
            if (Double.valueOf(minTemperature) < Double.valueOf(temperatures.get(i))) {
                return true;
            }
        }
        return false;
    }
}
