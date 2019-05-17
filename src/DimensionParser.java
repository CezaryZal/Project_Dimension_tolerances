
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DimensionParser {
    private static final Pattern pattern = Pattern.compile("([1-9][0-9]*)([a-zA-Z])([1-9][0-8]*)");

    private int valueOfDimension;
    private char symbolFromInput;
    private boolean isSymbolOverHh;
    private int valueITFromInput;

    public Dimension create(String input) {
        DeviationFromToleranceDb deviation = new DeviationFromToleranceDb();

        shareInput(input);

        int deviationByValueAndSymbol = deviation.getDeviationByValueAndSymbol(valueOfDimension,
                symbolFromInput, isSymbolOverHh, valueITFromInput);
        int deviationByValueAndIt = deviation.getDeviationByValueAndIt(valueOfDimension, valueITFromInput);

        return new Dimension(valueOfDimension, makeLowerDeviation(deviationByValueAndSymbol, deviationByValueAndIt),
                makeUpperDeviation(deviationByValueAndSymbol, deviationByValueAndIt));
    }

    public void shareInput(String input) {

        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()){
            valueOfDimension = Integer.valueOf(matcher.group(1));
            symbolFromInput = matcher.group(2).charAt(0);
            valueITFromInput = Integer.valueOf(matcher.group(3));
        } else {
            Matcher matcher1 = pattern.matcher(input);
            if (matcher1.find()){
                valueOfDimension = Integer.valueOf(matcher1.group(1));
                symbolFromInput = matcher1.group(2).charAt(0);
                valueITFromInput = Integer.valueOf(matcher1.group(3));
            } else {
                System.out.println("Wymiar został niewłaściwie wpisany. Proszę spróbować ponownie");
            }
        }
        isSymbolOverHh = Character.toLowerCase(symbolFromInput) > 'h' && Character.toLowerCase(symbolFromInput) < 'z';
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
