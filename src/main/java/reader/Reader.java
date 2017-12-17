package reader;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class Reader {


    private static final int LENGTH_OF_FIXED_STRING_7 = 7;
    private static final int LENGTH_OF_FIXED_STRING_8 = 8;
    private static final int LENGTH_OF_FIXED_STRING_14 = 14;
    private static final int LENGTH_OF_FIXED_STRING_18 = 18;
    private static final int NR_OF_TEMPERATURES_IN_3_DAYS = 24;


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

    public String readCoordinatesFrom(String report, String requestType) {
        int index = report.indexOf("\"coord\":{");
        if (requestType.equals("CurrentWeather")) {
            int lastIndex = report.indexOf(",\"weather\"");
            return report.substring(index + LENGTH_OF_FIXED_STRING_8, lastIndex);
        } else {
            int lastIndex = report.indexOf(",\"country\"");
            return report.substring(index + LENGTH_OF_FIXED_STRING_8, lastIndex);
        }

    }

    public String readNameFrom(String report, String requestType) {
        int index = report.indexOf("\"name\":\"");
        if (requestType.equals("CurrentWeather")) {
            int lastIndex = report.indexOf("\",\"cod\"");
            return report.substring(index + LENGTH_OF_FIXED_STRING_8, lastIndex);
        } else {
            int lastIndex = report.indexOf("\",\"coord\"");
            return report.substring(index + LENGTH_OF_FIXED_STRING_8, lastIndex);
        }
    }

    public String readCurrentTemperatureFrom(String report) {
        int index = report.indexOf("\"temp\":");
        String finalTemp = "";
        String temp = report.substring(index + LENGTH_OF_FIXED_STRING_7, index + LENGTH_OF_FIXED_STRING_14);
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
            String temp = report.substring(index + match.length(), index + LENGTH_OF_FIXED_STRING_18);

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
            if (temperatures.size() == NR_OF_TEMPERATURES_IN_3_DAYS) {
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
            String temp = report.substring(index + match.length(), index + LENGTH_OF_FIXED_STRING_18);
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
            if (temperatures.size() == NR_OF_TEMPERATURES_IN_3_DAYS) {
                return temperatures;
            }
        }
        return temperatures;
    }

    public String readOutputSample() throws IOException {
        File file = new File("C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\samples\\sampleOutput.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String inputLine;
        StringBuilder sample = new StringBuilder();
        while ((inputLine = br.readLine()) != null) {
            sample.append(inputLine);
        }
        br.close();
        return String.valueOf(sample);
    }

    public String readFullReportSample() throws IOException {
        File file = new File("C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\samples\\fullReportSample.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String inputLine;
        StringBuilder sample = new StringBuilder();
        while ((inputLine = br.readLine()) != null) {
            sample.append(inputLine);
        }
        br.close();
        return String.valueOf(sample);
    }
}
