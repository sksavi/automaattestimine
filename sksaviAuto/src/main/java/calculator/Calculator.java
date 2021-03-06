package calculator;

import java.util.List;

public class Calculator {
    public static String findMaxTemperature(List<String> temperatures) {
        double lowestTemp = 99999;
        for (int i = 0; i < temperatures.size(); i++) {
            if (Double.valueOf(temperatures.get(i)) < lowestTemp) {
                lowestTemp = Double.valueOf(temperatures.get(i));
            }
        }
        return String.valueOf(lowestTemp);
    }
}
