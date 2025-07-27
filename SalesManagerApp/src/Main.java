import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DatabaseHelper.initializeDatabase();
        String url = "jdbc:sqlite:SalesManager.db";
        SwingUtilities.invokeLater(()-> new SalesManagerGui().setVisible(true));


        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {



                /*SalesDAO salesDAO = new SalesDAO();
                Sale s = new Sale(2,1, LocalDateTime.now());
                salesDAO.InsertSale(s);
                List<Sale> SalesList =  salesDAO.GetAllSales();
                for(Sale s1 : SalesList){
                    System.out.printf("🔸 ID: %d | Product_id: %d | Quantity: %d%n | Date: %s%n",
                            s1.getId(), s1.getProductId(), s1.getQuantitySold(), s1.getSaleDate().toString());
                }

                String sql = "SELECT sales.id, products.name, sales.quantity_sold from sales join products on sales.product_id = products.id";
                List<String> salesjoin = new ArrayList<>();

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                int i=0;
                while (rs.next()) {

                    String salesid = rs.getString("id");
                    String productname = rs.getString("name");
                    String salesquantity = rs.getString("quantity_sold");
                    salesjoin.add(salesid + " " + productname+ " " +salesquantity);
                    System.out.println(salesjoin.get(i));
                    i++;
                }*/


               /*ProductDAO productDAO = new ProductDAO();
                Product p = productDAO.getProductById(2);
                p.setQuantity(1);
                productDAO.UpdateProduct(p);
                System.out.printf("🔸 ID: %d | Name: %s | Price: %.2f | Quantity: %d%n",
                        p.getId(), p.getName(), p.getPrice(), p.getQuantity());*/

              /* System.out.println("✅ Σύνδεση με βάση επιτυχής.");

                Scanner sc = new Scanner(System.in);
                System.out.println("Give name:");
                String nameinput = sc.nextLine();
                System.out.println("Give price:");
                Double pricein = sc.nextDouble();
                System.out.println("Give quantity:");
                int quantityin = sc.nextInt();

                //Insert Data from input
                ProductDAO productDAO = new ProductDAO();
                Product product = new Product(nameinput, pricein, quantityin);
                productDAO.addProduct(product);


                SalesDAO salesDAO = new SalesDAO();
                Sale s = new Sale(productDAO.getProductIdByName(nameinput),3, LocalDateTime.now());
                salesDAO.InsertSale(s);
                List<Sale> SalesList =  salesDAO.GetAllSales();
                for(Sale s1 : SalesList){
                    System.out.printf("🔸 ID: %d | Product_id: %d | Quantity: %d%n | Date: %s%n",
                            s1.getId(), s1.getProductId(), s1.getQuantitySold(), s1.getSaleDate().toString());
                }


                List<Product> productsList =  productDAO.getAllProducts();
                for(Product p : productsList){
                    System.out.printf("🔸 ID: %d | Name: %s | Price: %.2f | Quantity: %d%n",
                            p.getId(), p.getName(), p.getPrice(), p.getQuantity());


                }*/







            }
        } catch (SQLException e) {
            System.out.println("❌ Σφάλμα: " + e.getMessage());
        }
    }
}




