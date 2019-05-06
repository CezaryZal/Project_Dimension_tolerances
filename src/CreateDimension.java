public class CreateDimension {
    DatabaseDimensionTables databaseDimensionTables = new DatabaseDimensionTables();

    private int valueOfDimension = 0;


    public Dimension create(String input) {

        databaseDimensionTables.connect();
        Dimension dimension = new Dimension(makeValuesForDimension(input), makeLowerDeviation(input), makeUpperDeviation(input));
        databaseDimensionTables.disconnect();

        return dimension;

    }

    int makeValuesForDimension(String input) {
        String valueOfInputString = "";
        for (int i = 0; i <= input.length() - 2; i++) {
            if (Character.isDigit(input.charAt(i))) { // sprawdza czy to cyfra
                valueOfInputString += input.charAt(i);
            } else break;
        }
        valueOfDimension = Integer.parseInt(valueOfInputString);
        return valueOfDimension;
    }

    int makeUpperDeviation(String input) {
        if (Character.isLowerCase(getLetterOfInput(input))) {
            return databaseDimensionTables.getDeviationByValueAndSymbol(valueOfDimension, getLetterOfInput(input));
        }
        return databaseDimensionTables.getDeviationByValueAndSymbol(valueOfDimension, getLetterOfInput(input)) +
                databaseDimensionTables.getDeviationByValueAndIt(valueOfDimension, getValueItFromInput(input));
    }

    int makeLowerDeviation(String input) {
        if (Character.isUpperCase(getLetterOfInput(input))) {
            return databaseDimensionTables.getDeviationByValueAndSymbol(valueOfDimension, getLetterOfInput(input));
        }
        return databaseDimensionTables.getDeviationByValueAndSymbol(valueOfDimension, getLetterOfInput(input)) +
                databaseDimensionTables.getDeviationByValueAndIt(valueOfDimension, getValueItFromInput(input));
    }

    char getLetterOfInput(String input) {
        for (int i = input.length() - 1; i > 1; i--) {
            if (!Character.isDigit(input.charAt(i))) {
                return input.charAt(i);
            }
        }
        return 'l'; // mozna dac wyjatek albo warunek sprawdzajacy czy jest jakis znak w input'cie
    }

    int getValueItFromInput(String input) {
        String valueOfIt = "";
        for (int i = input.length() - 2; i <= input.length() - 1; i++) {
            if (Character.isDigit(input.charAt(i))) {
                valueOfIt += input.charAt(i);
            }
        }
        return Integer.valueOf(valueOfIt);
    }



}
