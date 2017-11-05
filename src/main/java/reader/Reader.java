package reader;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class Reader {

    public Reader() {}

    public String readFrom(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();
        return String.valueOf(content);
    }

    public String readInputTxtFile() throws IOException {
        File file = new File("C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = br.readLine()) != null) {
            content.append(inputLine);
        }
        br.close();
        return String.valueOf(content);
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
