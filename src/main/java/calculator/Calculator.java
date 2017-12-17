package calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public Calculator() {}

    public List<Double> findMaxTemperaturesFrom(List<String> temperatures) {
        List<Double> maxTemperatures = new ArrayList<>();

        double today = findHighestTempFrom(temperatures, 0, 8);
        double tomorrow = findHighestTempFrom(temperatures, 8, 16);
        double afterTomorrow = findHighestTempFrom(temperatures, 16, 24);

        maxTemperatures.add(today);
        maxTemperatures.add(tomorrow);
        maxTemperatures.add(afterTomorrow);

        return maxTemperatures;
    }

    public double findHighestTempFrom(List<String> temperatures, int from, int to) {
        double highestTemp = 0;
        for (int i = from; i < to; i++) {
            if (Double.valueOf(temperatures.get(i)) > highestTemp) {
                highestTemp = Double.valueOf(temperatures.get(i));
            }
        }
        return highestTemp;
    }

    public List<Double> findMinTemperaturesFrom(List<String> temperatures) {
        List<Double> minTemperatures = new ArrayList<>();

        double today = findLowestTempFrom(temperatures, 0, 8);
        double tomorrow = findLowestTempFrom(temperatures, 8, 16);
        double afterTomorrow = findLowestTempFrom(temperatures, 16, 24);

        minTemperatures.add(today);
        minTemperatures.add(tomorrow);
        minTemperatures.add(afterTomorrow);

        return minTemperatures;
    }

    public double findLowestTempFrom(List<String> temperatures, int from, int to) {
        double lowestTemp = 99999;

        for (int i = from; i < to; i++) {
            if (Double.valueOf(temperatures.get(i)) < lowestTemp) {
                lowestTemp = Double.valueOf(temperatures.get(i));
            }
        }
        return lowestTemp;
    }
}
