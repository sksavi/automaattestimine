package validator;

import java.util.List;

public class Validator {

    public static boolean validateFormat(String data) {
        return data.startsWith("{") && data.endsWith("}");
    }

    public static boolean validateCoord(String data) {
        return data.contains("{\"lon\":24.75,\"lat\":59.44}") || data.contains("{\"lat\":59.437,\"lon\":24.7535}");
    }

    public static boolean validateTemperatureFormat(String temperature) {
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

    public static boolean confirmMaxTemperature(String maxTemperature, List<String> temperatures) {
        for (int i = 0; i < temperatures.size() ; i++) {
            if (Double.valueOf(maxTemperature) < Double.valueOf(temperatures.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean confirmMinTemperature(String minTemperature, List<String> temperatures) {
        for (int i = 0; i < temperatures.size() ; i++) {
            if (Double.valueOf(minTemperature) < Double.valueOf(temperatures.get(i))) {
                return true;
            }
        }
        return false;
    }
}
