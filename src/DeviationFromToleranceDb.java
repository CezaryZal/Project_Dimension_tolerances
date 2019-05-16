import java.util.List;

public class DeviationFromToleranceDb {

    ToleranceDao toleranceDao = new ToleranceDao();

    public int getDeviationByValueAndSymbol(int valueOfDimension, char symbolFromInput, boolean symbolIsOverHh, int valueToleranceClass) {

        if (Character.isLowerCase(symbolFromInput)) {
            if (symbolIsOverHh) {
                String nameTable = "basic_deviations_for_shaft_over_symbol_h";

                return toleranceDao.getDeviationFromShaftOverHTable(
                        getValueOfRecord(valueOfDimension, nameTable), String.valueOf(symbolFromInput));

            } else {
                String nameTable = "basic_deviations_for_shaft_under_symbol_h";

                return toleranceDao.getDeviationFromShaftUnderHTable(
                        getValueOfRecord(valueOfDimension, nameTable), String.valueOf(symbolFromInput));
            }
        } else {
            if (symbolIsOverHh) {
                String nameTable = "basic_deviations_for_hole_over_symbol_h";

                return toleranceDao.getDeviationFromHoleOverHTable(
                        getValueOfRecord(valueOfDimension, nameTable), String.valueOf(symbolFromInput));

            } else {
                String nameTable = "basic_deviations_for_hole_under_symbol_h";

                int deviation = toleranceDao.getDeviationFromHoleUnderHTable(
                        getValueOfRecord(valueOfDimension, nameTable), String.valueOf(symbolFromInput));

                if (symbolFromInput > 74 && symbolFromInput < 80 && valueOfDimension > 3) {
                    String nameAddTable = "addition_for_hole_over_symbol_h";

                    int addValueDelta = toleranceDao.getDeviationFromAdditionTable(
                            getValueOfRecord(valueOfDimension, nameAddTable), valueToleranceClass);

                    return deviation + addValueDelta;
                }
                return deviation;
            }
        }
    }

    public int getDeviationByValueAndIt(int valueOfDimension, int valueToleranceClass) {
        String nameTable = "nominal_tolerance";

        return toleranceDao.getDeviationByValueAndIt(getValueOfRecord(valueOfDimension, nameTable),valueToleranceClass);
    }

    public int getValueOfRecord (int valueOfDimension, String nameTable){
        List <Integer> listDimensionsOfRecords = toleranceDao.getListOfLowerDimension(nameTable);

        int lastDimension = 0;

        for (Integer value : listDimensionsOfRecords){
            if (valueOfDimension >= lastDimension && valueOfDimension < value) {
                break;
            }
            lastDimension = value;
        }
        return lastDimension;
    }
}
