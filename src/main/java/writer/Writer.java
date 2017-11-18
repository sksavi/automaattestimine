package writer;

import reader.Reader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;

public class Writer {

    public Writer() {}

    public void writeToFile(Reader reader, HttpURLConnection con, String requestType) throws IOException {
        String report = reader.readFrom(con);
        String cityName = reader.readNameFrom(report, requestType);

        if (requestType.equals("CurrentWeather")) {
            String path = "C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\output\\" + cityName + "Current.txt";
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(report);
            writer.close();
        }

        if (requestType.equals("Forecast")) {
            String path = "C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\output\\" + cityName + "Forecast.txt";
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(report);
            writer.close();
        }
    }

    public void writeToFileBETTER(String report, String cityName, String requestType) throws IOException {
        if (requestType.equals("CurrentWeather")) {
            String path = "C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\output\\" + cityName + "Current.txt";
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(report);
            writer.close();
        }

        if (requestType.equals("Forecast")) {
            String path = "C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\output\\" + cityName + "Forecast.txt";
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(report);
            writer.close();
        }
    }
}
