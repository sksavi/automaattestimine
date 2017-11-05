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
        String content = reader.readFrom(con);

        if (requestType.equals("CurrentWeather")) {
            File file = new File("C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\currentWeatherOutput.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
        }

        if (requestType.equals("Forecast")) {
            File file = new File("C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\forecastOutput.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
        }
    }
}
