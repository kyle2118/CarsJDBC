package murdash;

import java.sql.*;

/**
 * To let user not to care about connection.
 * Other classes are allowed just to get Data(ResultSet),
 * Open and Close connection.
 */
public class DBHelper {
    // instance of connection
    private Connection connection = null;
    private Statement statement = null;
    /**
     * Constants are used to create connnection via url using username, password
     * driver name is used to let ClassLoader upload driver
     */
    private static final String JDBC = "com.mysql.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "osh";
    private static final String URL = "jdbc:mysql://localhost:3306";

    /**
     * Only makes ClassLoader upload jdbc driver.
     *
     */
    public DBHelper() {
        try {
            Class.forName(JDBC);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + JDBC + ". " + e);
        }
    }

    /**
     * Creates connection only,
     * in case of failure, further interaction with database is impossible (return false)
     */
    public boolean getConnection() {
        try {
          connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Unable to connect to database.");
            return false;
        }
        return true;
    }

    /**
     * Creates Statement and Executes Sql query (@param)
     * User will get in troubles in case of SQLException,
     * Since next method returns null, which make cause @NullPointerException
     */
    public ResultSet getResult(String sqlQuery) {
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

    /**
     * Closes TCP/IP connection. Method has been created,
     * in case of long connection.
     */
    public boolean close() {
        try {
            statement.close();
            connection.close();
        } catch(SQLException e) {
            System.err.println("Unable to close connection and statement");
            return false;
        }
        return true;
    }
}
