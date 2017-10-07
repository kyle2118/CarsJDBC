package TableHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import murdash.Tables;

import murdash.DBHelper;

/**
 *  Next class is a interface to work with Cars table only.
 *  Creates connection using DBHelper class.
 */
public class CarsTable {
    private DBHelper database;
    private ResultSet resultSet = null;
    /**
     *  Columns:
     *      id, model, idBrand, idType, year, MPG in 'Cars'.'Schema' database
     *
     */
    private int id;
    private String model;
    private int idBrand;
    private int idType;
    private int year;
    private int mpg;
    /**
     *  Next two fields are names of specific brand or type using 'idBrand' or 'idType'
     *  idBrand & idType are foreign keys pointing to
     *  'CarsSchema'.'BrandTable' and 'CarsSchema'.'TypeTable' tables, respectively
     */
    private String nameBrand;
    private  String nameType;

    /**
     * @param query
     * @throws SQLException
     *
     * Constructor creates a TCP/IP connection and holds until close() method is called.
     * Retrieves data from database in case of successful connection,
     * otherwise throws SQLException
     */
    public CarsTable(String query) throws SQLException{
        database = new DBHelper();
        if (database.getConnection()) {
            resultSet = database.getResult(query);
        }
        else throw new SQLException();
    }

    /**
     * @return
     * @throws SQLException
     *
     * Reads a line from ResultSet.
     * And assigns all instance fields from line.
     * Line looks like: id model(String) idBrand(fk) idType(fk) year(int) mpg(int);
     * Additionally, gets a name of a specific brand using idBrand
     * Gets a name of a specific type using idType
     */
    public boolean nextLine() throws SQLException {
        if (resultSet.next()) {
            id = resultSet.getInt("id");
            model = resultSet.getString("model");

            idBrand = resultSet.getInt("idBrand");
            String brandQuery = "select * from CarSchema." + Tables.Brand + " where id=" + idBrand;
            BrandTable temp = new BrandTable(brandQuery);
            temp.nextLine();
            nameBrand = temp.getName();

            idType = resultSet.getInt("idType");
            String typeQuery = "select * from CarSchema." + Tables.Type + " where id=" + idType;
            TypeTable tt = new TypeTable(typeQuery);
            tt.nextLine();
            nameType = tt.getName();

            year = resultSet.getInt("year");
            mpg = resultSet.getInt("MPG");
            return true;
        }
        return false;
    }

    /**
     * Uses DBHelper's close method, which closes connection and statement.
     */
    public boolean close() {
        return database.close();
    }
    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getIdBrand() {
        return idBrand;
    }
    public String getNameBrand() {
        return nameBrand;
    }

    public int getIdType() {
        return idType;
    }
    public String getNameType() {
        return nameType;
    }

    public int getYear() {
        return year;
    }

    public int getMpg() {
        return mpg;
    }
}
