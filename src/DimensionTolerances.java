import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DimensionTolerances {
    public static void main(String[] args) {


        DimensionParser dimensionDimension = new DimensionParser();
        Scanner scanerInput = new Scanner(System.in);
        String input = "251K8";
        while (!input.equals("stop")) {
            System.out.println("Wprowadz wymiary (np. 45g9)aby otrzymac odchylenie gorne i dolne lub wpisz \"stop\" aby zakonczyc wpisywanie");
            input = scanerInput.nextLine();

            // add some exception

            if (input.equals("stop")) {
                System.out.println("Zakonczono wypisywanie odchylek wymiaru.");
                break;
            }
            Dimension dimension = dimensionDimension.create(input);
            System.out.println(dimension);
        }
    }
}
