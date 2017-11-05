import java.util.Scanner;

/**
 * Created by hp on 23.10.2017.
 */
public class Test {
    private final String cityName;
    private final String requestType;

    public Test() {
        this.cityName = getCityNameFromUser();
        this.requestType = getRequestTypeFromUser();
        System.out.println(cityName);
        System.out.println(requestType);
    }

    private String getCityNameFromUser() {
        Scanner scanner = new Scanner (System.in);
        System.out.print("Enter city name ");
        return scanner.next();
    }

    private String getRequestTypeFromUser() {
        Scanner scanner = new Scanner (System.in);
        System.out.print("Enter request type 'CurrentWeather' or 'Forecast' ");
        return scanner.next();
    }

    public static void main(String[] args) {
        new Test();
    }
}
