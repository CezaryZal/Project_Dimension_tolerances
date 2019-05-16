
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DimensionParser {
    private DeviationFromToleranceDb deviation = new DeviationFromToleranceDb();
    private static final Pattern pattern = Pattern.compile("(([1-9][0-9]*)([a-zA-Z])([1-9][0-8]*))");

    private char symbolFromInput;
    private boolean symbolIsOverHh;

    public Dimension create(String input) {
        int valueOfDimension = getValuesForDimension(input);
        symbolFromInput = getLetterOfInput(input);

        symbolIsOverHh = Character.toLowerCase(getLetterOfInput(input)) > 105 && Character.toLowerCase(getLetterOfInput(input)) < 120;

        int deviationByValueAndSymbol = deviation.getDeviationByValueAndSymbol(valueOfDimension,
                symbolFromInput, symbolIsOverHh, getValueITFromInput(input));
        int deviationByValueAndIt = deviation.getDeviationByValueAndIt(valueOfDimension, getValueITFromInput(input));

        return new Dimension(valueOfDimension,
                makeLowerDeviation(deviationByValueAndSymbol, deviationByValueAndIt),
                makeUpperDeviation(deviationByValueAndSymbol, deviationByValueAndIt));
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

        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            valueOfInputString = matcher.group(2);
        }
        return Integer.parseInt(valueOfInputString);
    }

    private char getLetterOfInput(String input) {
        String lettersOfInput = "";

        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            lettersOfInput = matcher.group(3);
        }
        return lettersOfInput.charAt(0);
    }

    private int getValueITFromInput(String input) {
        String valueOfIt = "";

        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            valueOfIt = matcher.group(4);
        }
        return Integer.valueOf(valueOfIt);
    }
}
