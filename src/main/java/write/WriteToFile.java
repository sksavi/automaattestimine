package write;

import search.ReadResponse;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;

public class WriteToFile {

    public static void write(HttpURLConnection con) throws IOException {
        String content = ReadResponse.readFrom(con);
        File file = new File("C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\output.txt");

        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(content);
        bw.close();
    }
}
