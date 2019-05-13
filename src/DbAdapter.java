import java.sql.*;

public class DbAdapter {

    private String jdbUrl = "jdbc:mysql://localhost:3306/tolerancevalue?useSSL=false";
    private String username = "root";
    private String password = "SQLSerwer2019";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    /**
     * Connect to database
     */

    public Connection connect() {
        try {
            connection = DriverManager.getConnection(jdbUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Disconnect to database
     */

    public void disconnect (){
        try {
            if (connection != null){
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }

        } catch (Exception e){
            e.getMessage();
        }
    }
}
