package calculator;

import java.util.List;

public class Calculator {

    public Calculator() {}

    public String findMaxTemperatureFrom(List<String> temperatures) {
        double highestTemp = 0;
        for (int i = 0; i < temperatures.size(); i++) {
            if (Double.valueOf(temperatures.get(i)) > highestTemp) {
                highestTemp = Double.valueOf(temperatures.get(i));
            }
        }
        return String.valueOf(highestTemp);
    }

    public String findMinTemperatureFrom(List<String> temperatures) {
        double lowestTemp = 99999;
        for (int i = 0; i < temperatures.size(); i++) {
            if (Double.valueOf(temperatures.get(i)) < lowestTemp) {
                lowestTemp = Double.valueOf(temperatures.get(i));
            }
        }
        return String.valueOf(lowestTemp);
    }
}
