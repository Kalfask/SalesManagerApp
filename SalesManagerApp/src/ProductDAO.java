import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final String url = "jdbc:sqlite:SalesManager.db";

    public ProductDAO() {
        Connection conn = null;
    }
    public  void addProduct(Product product) {

        String  sql = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,product.getName());
            pstmt.setDouble(2,product.getPrice());
            pstmt.setInt(3,product.getQuantity());
            pstmt.executeUpdate();
            System.out.println("Product added successfully");

        } catch (SQLException e){
            System.out.println("Error inserting product");
        }
    }

    public  List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()){

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Product p = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"));
                products.add(p);
            }
        } catch (SQLException e){
            System.out.println("Error getting all products");
        }
        return products;
    }

    public void DeleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Product deleted successfully");
        } catch (SQLException e){
            System.out.println("Error deleting product");
        }

    }
    public void UpdateProduct(Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1,product.getName());
            pstmt.setDouble(2,product.getPrice());
            pstmt.setInt(3,product.getQuantity());
            pstmt.setInt(4,product.getId());
            pstmt.executeUpdate();
            System.out.println("Product updated successfully");
        } catch (SQLException e){
            System.out.println("Error updating product");
        }
    }

    public Product getProductById(int id) {
        Product product = null;

        String sql = "SELECT * FROM products WHERE id = ?";
        try(Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                product = new Product(rs.getInt("id"),rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity"));

            }

        }catch (SQLException e)
        {
            System.out.println("Error getting product by id");
        }
        return product;
    }

    public int getProductIdByName(String productName) {
        String sql = "SELECT * FROM products WHERE name = ?";
        try(Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,productName);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                return rs.getInt("id");
            }

        }catch (SQLException e)
        {
            System.out.println("Error getting product by name");
        }
        return 0;
    }

}
