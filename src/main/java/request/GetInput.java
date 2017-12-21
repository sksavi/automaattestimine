package request;


import java.util.Scanner;

public class GetInput {

    private final String cityName;

    public GetInput() {
        this.cityName = getCityNameFromUser();
    }
    public String getCityNameFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city name ");
        return scanner.next();
    }

    public String getCityName() {
        return cityName;
    }
}

