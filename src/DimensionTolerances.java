import java.util.Scanner;

public class DimensionTolerances {
    public static void main(String[] args) {


        DimensionParser dimensionParser = new DimensionParser();
        Scanner scanerInput = new Scanner(System.in);
        String input = "42g5";
//        while (!input.equals("stop")) {
//            System.out.println("Wprowadz wymiary (np. 45g9)aby otrzymac odchylenie gorne i dolne lub wpisz \"stop\" aby zakonczyc wpisywanie");
//            input = scanerInput.nextLine();
//
//
//            if (input.equals("stop")) {
//                System.out.println("Zakonczono wypisywanie odchylek wymiaru.");
//                break;
//            }

            System.out.println(dimensionParser.createDimensionTolerance(input));
        }
//    }
}
