
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DimensionParser {

    private int valueOfDimension;
    private char symbolFromInput;
    private boolean isSymbolOverHh;
    private int valueITFromInput;

    public Dimension createDimensionTolerance (String input) {
        DeviationFromToleranceDb deviation = new DeviationFromToleranceDb();

        shareInput(input);
        isSymbolOverHh = Character.toLowerCase(symbolFromInput) > 'h' && Character.toLowerCase(symbolFromInput) < 'z';

        int deviationByValueAndSymbol = deviation.getDeviationByValueAndSymbol(valueOfDimension,
                symbolFromInput, isSymbolOverHh, valueITFromInput);
        int deviationByValueAndIt = deviation.getDeviationByValueAndIt(valueOfDimension, valueITFromInput);

        return new Dimension(valueOfDimension, makeLowerDeviation(deviationByValueAndSymbol, deviationByValueAndIt),
                makeUpperDeviation(deviationByValueAndSymbol, deviationByValueAndIt));
    }

    public void shareInput(String input) {
        final Pattern pattern = Pattern.compile("([1-9][0-9]*)([a-zA-Z])([1-9][0-8]*)");

        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            valueOfDimension = Integer.valueOf(matcher.group(1));
            symbolFromInput = matcher.group(2).charAt(0);
            valueITFromInput = Integer.valueOf(matcher.group(3));
        } else {
            System.out.println("Wymiar został niewłaściwie wpisany. Proszę spróbować ponownie");
        }
    }

    public int makeUpperDeviation(int deviationByValueAndSymbol, int deviationByValueAndIt) {
        if (Character.isLowerCase(symbolFromInput)) {
            if (isSymbolOverHh) {
                return deviationByValueAndSymbol + deviationByValueAndIt;
            }
            return deviationByValueAndSymbol;
        }
        if (isSymbolOverHh){
            return deviationByValueAndSymbol;
        }
        return deviationByValueAndSymbol + deviationByValueAndIt;
    }

    public int makeLowerDeviation(int deviationByValueAndSymbol, int deviationByValueAndIt) {
        if (Character.isLowerCase(symbolFromInput)) {
            if (isSymbolOverHh){
                return deviationByValueAndSymbol;
            }
            return deviationByValueAndSymbol - deviationByValueAndIt;
        }
        if (isSymbolOverHh){
            return deviationByValueAndSymbol - deviationByValueAndIt;
        }
        return deviationByValueAndSymbol;
    }
}
