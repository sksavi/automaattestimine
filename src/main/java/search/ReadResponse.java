package search;

import java.io.*;
import java.net.HttpURLConnection;


public class ReadResponse {

    public static String readFrom(HttpURLConnection con) throws IOException {
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
}
