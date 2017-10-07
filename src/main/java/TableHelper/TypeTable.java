package TableHelper;

import murdash.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeTable {
    private DBHelper database;
    private ResultSet resultSet = null;
    private int id;
    private String name;
    private int capacity;


    public TypeTable(String query) throws SQLException {
        database = new DBHelper();
        if (database.getConnection()) {
            resultSet = database.getResult(query);
        }
    }
    public boolean nextLine() throws SQLException {
        if (resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            capacity = resultSet.getInt("capacity");
            return true;
        }
        return false;
    }
    public boolean close() { return database.close(); }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public int getCapacity() {
        return capacity;
    }
}
