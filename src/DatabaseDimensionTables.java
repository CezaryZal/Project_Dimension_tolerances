import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDimensionTables extends DbAdapter {


    public int getDeviationByValueAndSymbol(int valueOfDimension, char symbolFromInput, boolean symbolIsOverHh, int valueToleranceClass) {

        String SQLDeviationForSymbol;
        String columnLabelForSymbol = "" + symbolFromInput;
        String SQLLowerDimensionForSymbol = "SELECT * FROM basic_deviations_for_shaft_under_symbol_h";
        int lowerDimension = getLowerDimension(valueOfDimension, SQLLowerDimensionForSymbol);
        int symbolInteger = symbolFromInput;

        if (Character.isLowerCase(symbolFromInput)) {
            if (symbolIsOverHh) {
                SQLDeviationForSymbol = "SELECT * FROM basic_deviations_for_shaft_over_symbol_h WHERE " +
                        "`lower_dimension[mm]`=" + lowerDimension;
            } else {
                SQLDeviationForSymbol = "SELECT * FROM basic_deviations_for_shaft_under_symbol_h WHERE " +
                        "`lower_dimension[mm]`=" + lowerDimension;
            }
        } else {
            if (symbolIsOverHh) {
                SQLDeviationForSymbol = "SELECT * FROM basic_deviations_for_hole_over_symbol_h WHERE " +
                        "`lower_dimension[mm]`=" + lowerDimension;
            } else {
                SQLDeviationForSymbol = "SELECT * FROM basic_deviations_for_hole_under_symbol_h WHERE " +
                        "`lower_dimension[mm]`=" + lowerDimension;
            }
        }
        if (symbolInteger > 74 && symbolInteger < 80 && valueOfDimension > 3){

            String SQLLowerDimensionForAdaValue = "SELECT * FROM addition_for_hole_over_symbol_h";
            String SQLAddDeviationForSymbol = "SELECT * FROM addition_for_hole_over_symbol_h WHERE `lower_dimension[mm]`=" +
                    getLowerDimension(valueOfDimension, SQLLowerDimensionForAdaValue);
            String columnLabelAddForSymbol = "IT" + valueToleranceClass;
            int addValueDelta = getDeviationFromDB(SQLAddDeviationForSymbol, columnLabelAddForSymbol);

            return getDeviationFromDB(SQLDeviationForSymbol, columnLabelForSymbol) + addValueDelta;
        }
        return getDeviationFromDB(SQLDeviationForSymbol, columnLabelForSymbol);
    }

    public int getDeviationByValueAndIt(int valueOfDimension, int valueToleranceClass) {
        String SQLLowerDimensionForIT = "SELECT * FROM nominal_tolerance";
        String SQLDeviationForIT = "SELECT * FROM nominal_tolerance WHERE `lower_dimension[mm]`=" +
                getLowerDimension(valueOfDimension, SQLLowerDimensionForIT);
        String columnLabelForIT = "IT" + valueToleranceClass + "[um]";

        return getDeviationFromDB(SQLDeviationForIT, columnLabelForIT);
    }


    private int getLowerDimension(int valueOfDimension, String SQLLowerDimension) {
        int lastDimension = 0;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQLLowerDimension);

            int currentDimension;

            while (resultSet.next()) {
                currentDimension = resultSet.getInt("lower_dimension[mm]");
                if (valueOfDimension >= lastDimension && valueOfDimension < currentDimension) {
                    break;
                }
                lastDimension = currentDimension;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastDimension;
    }

    private int getDeviationFromDB(String SQLDeviation, String columnLabel) {
        int valueFromDB = 0;

        try {
            PreparedStatement stat = connection.prepareStatement(SQLDeviation);
            resultSet = stat.executeQuery();

            while (resultSet.next()) {
                valueFromDB = resultSet.getInt(columnLabel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valueFromDB;
    }
}
