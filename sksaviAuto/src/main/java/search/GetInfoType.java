package search;

import java.util.ArrayList;
import java.util.List;

public class GetInfoType {

    public static String temperature(String data) {
        int index = data.indexOf("\"temp\":");
        String finalTemp = "";
        String temp = data.substring(index + 7, index + 14);
        for (int i = 0; i < temp.length(); i++) {
            if(i == 3) {
                finalTemp += ".";
            }
            if (Character.isDigit(temp.charAt(i))) {
                finalTemp += temp.charAt(i);
            }
        }
        return finalTemp;
    }

    public static List<String> temperatures(String data) {
        List<String> temperatures = new ArrayList<>();
        String match = "\"temp\":";
        String finalTemp = "";

        int index = data.indexOf(match);
        while (index >= 0) {
            index = data.indexOf(match, index + 1);
            String temp = data.substring(index + 7, index + 14);

            for (int i = 0; i < temp.length(); i++) {
                if(i == 3) {
                    finalTemp += ".";
                }
                if (Character.isDigit(temp.charAt(i))) {
                    finalTemp += temp.charAt(i);
                }
            }
            temperatures.add(finalTemp);
            finalTemp = "";
            if (temperatures.size() == 24) {
                return temperatures;
            }
        }
        return temperatures;
    }
}
