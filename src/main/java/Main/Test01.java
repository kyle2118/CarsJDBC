package Main;

import TableHelper.CarsTable;
import murdash.Tables;

import java.sql.SQLException;

/**
 *  User does not see inner condition of a connection.
 *  Moreover there is no need to check result sets and retrieve data from it.
 *  TableHelper package's classes are 'wrapper' to work with specific table.
 */
public class Test01 {
    public static void main(String[] args) {
        try {
            CarsTable cars = new CarsTable("select * from CarSchema." + Tables.Cars);


            System.out.printf("%-3s %-17s %-17s %-12s %-5s %-3s\n", "id", "Model", "nameBrand", "nameType", "year", "MPG");
            while (cars.nextLine()) {
                System.out.printf("%-3d %-17s %-17s %-12s %-5d %-3d\n",
                        cars.getId(), cars.getModel(), cars.getNameBrand(),
                        cars.getNameType(), cars.getYear(), cars.getMpg());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            BrandTable brands = new BrandTable("select * from CarSchema." + Tables.Brand);
//            System.out.printf("%-3s %-15s %-13s", "id", "name", "continent");
//            while (brands.nextLine()) {
//                System.out.printf("%-3d %-15s %-13s",
//                        brands.getId(), brands.getName(), brands.getNameCountry());
//            }
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }

    }
}
