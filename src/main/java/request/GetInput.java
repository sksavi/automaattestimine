package request;


import java.util.Scanner;

public class GetInput {

    private final String cityName;
    private final String requestType;

    public GetInput() {
        this.cityName = getCityNameFromUser();
        this.requestType = getRequestTypeFromUser();
    }
    public String getCityNameFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city name ");
        return scanner.next();
    }

    public String getRequestTypeFromUser() {
        Scanner scanner = new Scanner (System.in);
        System.out.print("Enter request type 'CurrentWeather' or 'Forecast' ");
        return scanner.next();
    }

    public String getCityName() {
        return cityName;
    }

    public String getRequestType() {
        return requestType;
    }
}

