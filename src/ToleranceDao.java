import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToleranceDao {

    public List <Integer> getListOfLowerDimension(String nameTable) {
        Statement statement;
        ResultSet resultSet;
        List<Integer> listDimensionsOfRecords = new ArrayList<>();

        try (Connection connection = Databases.getConnection()) {

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM " + nameTable);

            while (resultSet.next()) {
                listDimensionsOfRecords.add(resultSet.getInt("lower_dimension[mm]"));
            }
        } catch (SQLException error) {
            System.out.println("Wysątpienie błedu. Proszę sprawdzić poprawność wpisania wymiaru.");
            System.out.println("Wychwycony błąd: " + error.getMessage());
        }
        return listDimensionsOfRecords;
    }

    public int getDeviationByValueAndIt (int valueOfRecord, int valueToleranceClass){
        ResultSet resultSet;
        int valueFromDB = 0;
        String sqlQuery = "SELECT * FROM nominal_tolerance WHERE `lower_dimension[mm]`=?";
        String columnLabelForIT = "IT" + valueToleranceClass + "[um]";

        try (Connection connection = Databases.getConnection()) {

            PreparedStatement stat = connection.prepareStatement(sqlQuery);
            stat.setInt(1, valueOfRecord);
            resultSet = stat.executeQuery();

            while (resultSet.next()) {
                valueFromDB = resultSet.getInt(columnLabelForIT);
            }

        } catch (SQLException error) {
            System.out.println("Wysątpienie błedu. Proszę sprawdzić poprawność wpisania wymiaru.");
            System.out.println("Wychwycony błąd: " + error.getMessage());
        }
        return valueFromDB;
    }

    public int getDeviationFromHoleOverHTable (int valueOfRecord1, String symbolFromInput){
        ResultSet resultSet;
        int valueFromDB = 0;
        String sqlQuery = "SELECT * FROM basic_deviations_for_hole_over_symbol_h WHERE `lower_dimension[mm]` = ?";

        try (Connection connection = Databases.getConnection()) {

            PreparedStatement stat = connection.prepareStatement(sqlQuery);
            stat.setInt(1, valueOfRecord1);
            resultSet = stat.executeQuery();

            while (resultSet.next()) {
                valueFromDB = resultSet.getInt(symbolFromInput);
            }

        } catch (SQLException error) {
            System.out.println("Wysątpienie błedu. Proszę sprawdzić poprawność wpisania wymiaru.");
            System.out.println("Wychwycony błąd: " + error.getMessage());
        }
        return valueFromDB;
    }

    public int getDeviationFromHoleUnderHTable (int valueOfRecord, String symbolFromInput){
        ResultSet resultSet;
        int valueFromDB = 0;
        String sqlQuery = "SELECT * FROM basic_deviations_for_hole_under_symbol_h WHERE `lower_dimension[mm]` = ?";

        try (Connection connection = Databases.getConnection()) {

            PreparedStatement stat = connection.prepareStatement(sqlQuery);
            stat.setInt(1, valueOfRecord);
            resultSet = stat.executeQuery();

            while (resultSet.next()) {
                valueFromDB = resultSet.getInt(symbolFromInput);
            }

        } catch (SQLException error) {
            System.out.println("Wysątpienie błedu. Proszę sprawdzić poprawność wpisania wymiaru.");
            System.out.println("Wychwycony błąd: " + error.getMessage());
        }
        return valueFromDB;
    }

    public int getDeviationFromShaftOverHTable (int valueOfRecord, String symbolFromInput){
        ResultSet resultSet;
        int valueFromDB = 0;
        String sqlQuery = "SELECT * FROM basic_deviations_for_shaft_over_symbol_h WHERE `lower_dimension[mm]` = ?";

        try (Connection connection = Databases.getConnection()) {

            PreparedStatement stat = connection.prepareStatement(sqlQuery);
            stat.setInt(1, valueOfRecord);
            resultSet = stat.executeQuery();

            while (resultSet.next()) {
                valueFromDB = resultSet.getInt(symbolFromInput);
            }

        } catch (SQLException error) {
            System.out.println("Wysątpienie błedu. Proszę sprawdzić poprawność wpisania wymiaru.");
            System.out.println("Wychwycony błąd: " + error.getMessage());
        }
        return valueFromDB;
    }

    public int getDeviationFromShaftUnderHTable (int valueOfRecord, String symbolFromInput){
        ResultSet resultSet;
        int valueFromDB = 0;
        String sqlQuery = "SELECT * FROM basic_deviations_for_shaft_under_symbol_h WHERE `lower_dimension[mm]` = ?";

        try (Connection connection = Databases.getConnection()) {

            PreparedStatement stat = connection.prepareStatement(sqlQuery);
            stat.setInt(1, valueOfRecord);
            resultSet = stat.executeQuery();

            while (resultSet.next()) {
                valueFromDB = resultSet.getInt(symbolFromInput);
            }

        } catch (SQLException error) {
            System.out.println("Wysątpienie błedu. Proszę sprawdzić poprawność wpisania wymiaru.");
            System.out.println("Wychwycony błąd: " + error.getMessage());
        }
        return valueFromDB;
    }

    public int getDeviationFromAdditionTable (int valueOfRecord, int valueToleranceClass){
        ResultSet resultSet;
        int valueFromDB = 0;
        String sqlQuery = "SELECT * FROM addition_for_hole_over_symbol_h WHERE `lower_dimension[mm]` = ?";
        String columnLabelForIT = "IT" + valueToleranceClass;

        try (Connection connection = Databases.getConnection()) {

            PreparedStatement stat = connection.prepareStatement(sqlQuery);
            stat.setInt(1, valueOfRecord);
            resultSet = stat.executeQuery();

            while (resultSet.next()) {
                valueFromDB = resultSet.getInt(columnLabelForIT);
            }

        } catch (SQLException error) {
            System.out.println("Wysątpienie błedu. Proszę sprawdzić poprawność wpisania wymiaru.");
            System.out.println("Wychwycony błąd: " + error.getMessage());
        }
        return valueFromDB;
    }

}
