import reader.Reader;
import validator.Validator;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        String report = reader.readInputTxtFile();
        Validator validator = new Validator();
        boolean actual = validator.validateCoordinatesExist(report);
        System.out.println(actual);
    }
}
