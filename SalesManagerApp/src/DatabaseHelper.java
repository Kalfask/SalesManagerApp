import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

    public static void initializeDatabase()
    {
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:SalesManager.db");
            Statement stmt = conn.createStatement()){
                String createProducts = "CREATE TABLE IF NOT EXISTS products (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT NOT NULL," +
                        "price REAL NOT NULL," +
                        "quantity INTEGER NOT NULL);";

            String createSales = "CREATE TABLE IF NOT EXISTS sales (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "product_id INTEGER NOT NULL," +
                    "quantity_sold INTEGER NOT NULL," +
                    "sale_date TEXT NOT NULL," +
                    "FOREIGN KEY (product_id) REFERENCES products(id));";

            stmt.executeUpdate(createProducts);
            stmt.executeUpdate(createSales);



            System.out.println("Opened database successfully");
            System.out.println("And tables created");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
