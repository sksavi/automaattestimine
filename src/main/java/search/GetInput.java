package search;

import java.util.Scanner;

public class GetInput {

    public static String getCityNameFromUser() {
        Scanner scanner = new Scanner (System.in);
        System.out.print("Enter city name ");
        return scanner.next();
    }

    public static String getRequestTypeFromUser() {
        Scanner scanner = new Scanner (System.in);
        System.out.print("Enter request type 'CurrentWeather' or 'Forecast' ");
        return scanner.next();
    }
}
