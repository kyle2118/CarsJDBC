package TableHelper;

import murdash.DBHelper;
import murdash.Tables;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandTable {
    private DBHelper database;
    private ResultSet resultSet = null;
    private int id;
    private String name;
    private int idCountry;
    private String nameCountry;


    public BrandTable(String query) throws SQLException {
        database = new DBHelper();
        if (database.getConnection()) {
            resultSet = database.getResult(query);
        }
    }
    public boolean close() {
        return database.close();
    }
    public boolean nextLine() throws SQLException {
        if (resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");

            idCountry = resultSet.getInt("idCountry");
//            String query = "select * from Schema." + Tables.Country + " where id=" + idCountry;
//            CountryTable countries = new CountryTable(query);
//            countries.nextLine();
//            nameCountry = countries.getName();

            return true;
        }
        return false;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public int getIdCountry() {
        return idCountry;
    }
    public String getNameCountry() { return nameCountry; }
}
