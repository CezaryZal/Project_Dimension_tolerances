public class CreateDimension {
    DatabaseDimensionTables databaseDimensionTables = new DatabaseDimensionTables();

    private char symbolFromInput;
    private boolean symbolIsOverHh;

    public Dimension create(String input) {
        int valueOfDimension = getValuesForDimension(input);
        symbolIsOverHh = isSymbolOverHh(input);
        symbolFromInput = getLetterOfInput(input);

        databaseDimensionTables.connect();
        int deviationByValueAndSymbol = databaseDimensionTables.getDeviationByValueAndSymbol(valueOfDimension,
                symbolFromInput, symbolIsOverHh, getValueItFromInput(input));
        int deviationByValueAndIt = databaseDimensionTables.getDeviationByValueAndIt(valueOfDimension, getValueItFromInput(input));
        databaseDimensionTables.disconnect();

        Dimension dimension = new Dimension(valueOfDimension,
                makeLowerDeviation(deviationByValueAndSymbol, deviationByValueAndIt),
                makeUpperDeviation(deviationByValueAndSymbol, deviationByValueAndIt));

        return dimension;
    }

    private int makeUpperDeviation(int deviationByValueAndSymbol, int deviationByValueAndIt) {
        if (Character.isLowerCase(symbolFromInput)) {
            if (symbolIsOverHh) {
                return deviationByValueAndSymbol + deviationByValueAndIt;
            }
            return deviationByValueAndSymbol;
        }
        if (symbolIsOverHh){
            return deviationByValueAndSymbol;
        }
        return deviationByValueAndSymbol + deviationByValueAndIt;
    }

    private int makeLowerDeviation(int deviationByValueAndSymbol, int deviationByValueAndIt) {
        if (Character.isLowerCase(symbolFromInput)) {
            if (symbolIsOverHh){
                return deviationByValueAndSymbol;
            }
            return deviationByValueAndSymbol - deviationByValueAndIt;
        }
        if (symbolIsOverHh){
            return deviationByValueAndSymbol - deviationByValueAndIt;
        }
        return deviationByValueAndSymbol;
    }

    private int getValuesForDimension(String input) {
        String valueOfInputString = "";
        for (int i = 0; i <= input.length() - 2; i++) {
            if (Character.isDigit(input.charAt(i))) { // sprawdza czy to cyfra
                valueOfInputString += input.charAt(i);
            } else break;
        }
        return Integer.parseInt(valueOfInputString);
    }

    private char getLetterOfInput(String input) {
        for (int i = input.length() - 1; i >= 1; i--) {
            if (!Character.isDigit(input.charAt(i))) {
                return input.charAt(i);
            }
        }
        return 'l'; // mozna dac wyjatek albo warunek sprawdzajacy czy jest jakis znak w input'cie
    }

    private int getValueItFromInput(String input) {
        String valueOfIt = "";
        for (int i = input.length() - 2; i <= input.length() - 1; i++) {
            if (Character.isDigit(input.charAt(i))) {
                valueOfIt += input.charAt(i);
            }
        }
        return Integer.valueOf(valueOfIt);
    }

    private boolean isSymbolOverHh(String input) {
        char tmpSymbolFromInput = Character.toLowerCase(getLetterOfInput(input));
        if (tmpSymbolFromInput == 'j' || tmpSymbolFromInput == 'k' || tmpSymbolFromInput == 'm' || tmpSymbolFromInput == 'n' ||
                tmpSymbolFromInput == 'p' || tmpSymbolFromInput == 'r' || tmpSymbolFromInput == 's') {
            return true;
        }
        return false;
    }
}
