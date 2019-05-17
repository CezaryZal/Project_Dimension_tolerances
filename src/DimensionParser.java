
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DimensionParser {
    private DeviationFromToleranceDb deviation = new DeviationFromToleranceDb();
    private static final Pattern pattern = Pattern.compile("([1-9][0-9]*)([a-zA-Z])([1-9][0-8]*)");

    private Dimension dimension;

    public Dimension create(String input) {
        dimension = shareInput(input);



        int deviationByValueAndSymbol = deviation.getDeviationByValueAndSymbol(dimension.getValueOfDimension(),
                dimension.getSymbolFromInput(), dimension.isSymbolIsOverHh(), dimension.getValueITFromInput());
        int deviationByValueAndIt = deviation.getDeviationByValueAndIt(dimension.getValueOfDimension(),  dimension.getValueITFromInput());

        dimension.setLowerDeviation(makeLowerDeviation(deviationByValueAndSymbol, deviationByValueAndIt));
        dimension.setUpperDeviation(makeUpperDeviation(deviationByValueAndSymbol, deviationByValueAndIt));
        return dimension;
    }

    public Dimension shareInput(String input) {
        int inputValue = 0;
        char symbolFromInput = 'i';
        boolean symbolIsOverHh;
        int valueITFromInput = 0;

        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()){
            inputValue = Integer.valueOf(matcher.group(1));
            symbolFromInput = matcher.group(2).charAt(0);
            valueITFromInput = Integer.valueOf(matcher.group(3));
        } else {
            Matcher matcher1 = pattern.matcher(input);
            if (matcher1.find()){
                inputValue = Integer.valueOf(matcher1.group(1));
                symbolFromInput = matcher1.group(2).charAt(0);
                valueITFromInput = Integer.valueOf(matcher1.group(3));
            } else {
                System.out.println("Wymiar został niewłaściwie wpisany. Proszę spróbować ponownie");
            }
        }

        symbolIsOverHh = Character.toLowerCase(symbolFromInput) > 'h' && Character.toLowerCase(symbolFromInput) < 'z';

        return new Dimension(inputValue, symbolFromInput, symbolIsOverHh, valueITFromInput);
    }

    public int makeUpperDeviation(int deviationByValueAndSymbol, int deviationByValueAndIt) {
        if (Character.isLowerCase(dimension.getSymbolFromInput())) {
            if (dimension.isSymbolIsOverHh()) {
                return deviationByValueAndSymbol + deviationByValueAndIt;
            }
            return deviationByValueAndSymbol;
        }
        if (dimension.isSymbolIsOverHh()){
            return deviationByValueAndSymbol;
        }
        return deviationByValueAndSymbol + deviationByValueAndIt;
    }

    public int makeLowerDeviation(int deviationByValueAndSymbol, int deviationByValueAndIt) {
        if (Character.isLowerCase(dimension.getSymbolFromInput())) {
            if (dimension.isSymbolIsOverHh()){
                return deviationByValueAndSymbol;
            }
            return deviationByValueAndSymbol - deviationByValueAndIt;
        }
        if (dimension.isSymbolIsOverHh()){
            return deviationByValueAndSymbol - deviationByValueAndIt;
        }
        return deviationByValueAndSymbol;
    }
}
