public class Dimension {
    private int valueOfDimension;
    private int lowerDeviation;
    private int upperDeviation;

    public Dimension(int valueOfDimension, int lowerDeviation, int upperDeviation) {
        this.valueOfDimension = valueOfDimension;
        this.lowerDeviation = lowerDeviation;
        this.upperDeviation = upperDeviation;
    }

    @Override
    public String toString() {
        return "Wymiar glowny: " + valueOfDimension + "mm. \nWymiar dolnej odchylki wynosi: " +
                lowerDeviation + "um. \nWymiar gornej odchylki wynosi: " + upperDeviation + "um.";
    }
}

