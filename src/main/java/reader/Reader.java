package reader;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class Reader {


    private static final int LENGHTH_OF_FIXED_STRING = 8;

    public Reader() {}

    public String readFrom(HttpURLConnection connection) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder report = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            report.append(inputLine);
        }
        in.close();
        connection.disconnect();
        return String.valueOf(report);
    }

    public String readInputTxtFile() throws IOException {
        File file = new File("C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String inputLine;
        StringBuilder report = new StringBuilder();
        while ((inputLine = br.readLine()) != null) {
            report.append(inputLine);
        }
        br.close();
        return String.valueOf(report);
    }

    public String readNameFrom(String report, String requestType) {
        int index = report.indexOf("\"name\":\"");
        if (requestType.equals("CurrentWeather")) {
            int lastIndex = report.indexOf("\",\"cod\"");
            return report.substring(index + LENGHTH_OF_FIXED_STRING, lastIndex);
        } else {
            int lastIndex = report.indexOf("\",\"coord\"");
            return report.substring(index + LENGHTH_OF_FIXED_STRING, lastIndex);
        }
    }

    public String readCurrentTemperatureFrom(String report) {
        int index = report.indexOf("\"temp\":");
        String finalTemp = "";
        String temp = report.substring(index + 7, index + 14);
        for (int i = 0; i < temp.length(); i++) {
            if (i == 3) {
                finalTemp += ".";
            }
            if (Character.isDigit(temp.charAt(i))) {
                finalTemp += temp.charAt(i);
            }
        }
        return finalTemp;
    }

    public List<String> readMinTemperaturesFrom(String report) {
        List<String> temperatures = new ArrayList<>();
        String match = "\"temp_min\":";
        String finalTemp = "";

        int index = report.indexOf(match);
        while (index >= 0) {
            index = report.indexOf(match, index + 1);
            String temp = report.substring(index + 7, index + 14);

            for (int i = 0; i < temp.length(); i++) {
                if (i == 3) {
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

    public List<String> readMaxTemperaturesFrom(String report) {
        List<String> temperatures = new ArrayList<>();
        String match = "\"temp_max\":";
        String finalTemp = "";

        int index = report.indexOf(match);
        while (index >= 0) {
            index = report.indexOf(match, index + 1);
            String temp = report.substring(index + 7, index + 14);

            for (int i = 0; i < temp.length(); i++) {
                if (i == 3) {
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
