import java.util.Scanner;

public class DimensionTolerances {
    public static void main(String[] args) {


        CreateDimension dimensionDimension = new CreateDimension();
        Scanner scanerInput = new Scanner(System.in);
        String input = "251K8";
//        while (!input.equals("stop")) {
//            System.out.println("Wprowadz wymiary (np. 45g9)aby otrzymac odchylenie gorne i dolne lub wpisz \"stop\" aby zakonczyc wpisywanie");
//            input = scanerInput.nextLine();
//            if (input.equals("stop")) {
//                System.out.println("Zakonczono wypisywanie odchylek wymiaru.");
//                break;
//            }
            Dimension dimension = dimensionDimension.create(input);
            System.out.println(dimension);
//        }

    }
}
