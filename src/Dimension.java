public class Dimension {
    private int valueOfDimension;
    private char symbolFromInput;
    private boolean symbolIsOverHh;
    private int valueITFromInput;
    private int lowerDeviation;
    private int upperDeviation;

    public Dimension(int valueOfDimension, char symbolFromInput, boolean symbolIsOverHh, int valueITFromInput) {
        this.valueOfDimension = valueOfDimension;
        this.symbolFromInput = symbolFromInput;
        this.symbolIsOverHh = symbolIsOverHh;
        this.valueITFromInput = valueITFromInput;
    }

    public int getValueOfDimension() {
        return valueOfDimension;
    }

    public char getSymbolFromInput() {
        return symbolFromInput;
    }

    public boolean isSymbolIsOverHh() {
        return symbolIsOverHh;
    }

    public int getValueITFromInput() {
        return valueITFromInput;
    }

    public void setLowerDeviation(int lowerDeviation) {
        this.lowerDeviation = lowerDeviation;
    }

    public void setUpperDeviation(int upperDeviation) {
        this.upperDeviation = upperDeviation;
    }

    @Override
    public String toString() {
        return "Wymiar glowny: " + valueOfDimension + "mm. \nWymiar dolnej odchylki wynosi: " +
                lowerDeviation + "um. \nWymiar gornej odchylki wynosi: " + upperDeviation + "um.";
    }
}

