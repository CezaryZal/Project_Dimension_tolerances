public class Dimension {

    private int inputValue;
    private int lowerDeviation;
    private int upperDeviation;

    Dimension(int inputValue, int lowerDeviation, int upperDeviation) {
        this.inputValue = inputValue;
        this.lowerDeviation = lowerDeviation;
        this.upperDeviation = upperDeviation;
    }

    @Override
    public String toString () {
        return "Wymiar glowny: " + inputValue + "mm. \nWymiar dolnej odchylki wynosi: " +
                lowerDeviation + "um. \nWymiar gornej odchylki wynosi: " + upperDeviation + "um.";
    }
}
