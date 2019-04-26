import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseDimensionTables extends DbAdapter {


    public int getDeviationByValueAndSymbol(int valueOfDimension, int letterFromInput ){
        int deviationByValueAndSymbol = 0;

        return deviationByValueAndSymbol;
    }

    public int getDeviationByValueAndIt(int valueOfDimension, int valueToleranceClass){
        int deviationByValueAndIt = 0;

        int lastDimension = 0;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM nominal_tolerance");

            int currentDimension = 0;

            while (resultSet.next()) {
                currentDimension = resultSet.getInt("lower_dimension[mm]");
                if (valueOfDimension > lastDimension && valueOfDimension <= currentDimension ){
                    break;
                }
                lastDimension = currentDimension;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement stat = connection.prepareStatement("SELECT * FROM nominal_tolerance WHERE " +
                    "`lower_dimension[mm]`=?");
            stat.setInt(1, lastDimension);
            resultSet = stat.executeQuery();

            while (resultSet.next()){
                deviationByValueAndIt =  resultSet.getInt("IT" + valueToleranceClass + "[um]");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deviationByValueAndIt;
    }
}
