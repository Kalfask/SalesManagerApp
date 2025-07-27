import java.sql.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SalesDAO {
    private final String url = "jdbc:sqlite:SalesManager.db";

    public SalesDAO() {
        Connection conn = null;
    }

    public void InsertSale(Sale sale) {

        ProductDAO productDAO = new ProductDAO();
        Product p = productDAO.getProductById(sale.getProductId());


        if (p != null) {
            if (p.getQuantity() < sale.getQuantitySold()) {
                System.out.println("Not enough stock");
            } else {
                p.setQuantity(p.getQuantity() - sale.getQuantitySold());
                productDAO.UpdateProduct(p);
                String sql = "INSERT INTO sales (product_id, quantity_sold, sale_date) VALUES (?, ?, ?)";
                try (Connection conn = DriverManager.getConnection(url);
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, sale.getProductId());
                    pstmt.setInt(2, sale.getQuantitySold());
                    pstmt.setString(3, sale.getSaleDate().toString());
                    pstmt.executeUpdate();
                    System.out.println("Sale Recorded Successfully");

                } catch (SQLException e) {
                    System.err.println(e.getMessage());
                }

            }
        }



    }

    public List<Sale> GetAllSales() {
        String sql = "SELECT * FROM sales";
        List<Sale> sales = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int saleId = rs.getInt("id");
                int productId = rs.getInt("product_id");
                int quantitySold = rs.getInt("quantity_sold");
                String saleDate = rs.getString("sale_date");
                sales.add(new Sale(saleId, productId, quantitySold, LocalDateTime.parse(rs.getString("sale_date"))));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sales;
    }


    public void DeleteSale(Sale sale) {

            String sql = "DELETE FROM sales WHERE id = ?";
            try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setInt(1, sale.getId());
                pstmt.executeUpdate();
                System.out.println("Sale Deleted Successfully");
            }catch (SQLException e) {
                System.err.println(e.getMessage());
            }



    }


}
