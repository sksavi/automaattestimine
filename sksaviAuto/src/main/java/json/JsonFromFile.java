package json;

import java.io.*;

public class JsonFromFile {

    private static final String filename = "C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\json\\5daysWeather";
    //private static final String filename = "C:\\Users\\hp\\sksaviAuto\\src\\main\\java\\json\\currentWeather";

    public static String read() {
        BufferedReader br;
        String data = "";
        try {
            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = br.readLine()) != null) {
                data += line;
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}