import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class SalesPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    private SalesDAO salesDAO = new SalesDAO();
    private ProductDAO productDAO = new ProductDAO();

    public SalesPanel() {
        model = new DefaultTableModel( new Object[]{"Sale ID", "Product Name", "Quantity", "Date"},0);
        setLayout(new BorderLayout());
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Sale");
        JButton removeButton = new JButton("Remove Sale");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        loadSales();


        addButton.addActionListener(e -> {
            List<Product> products = productDAO.getAllProducts();
            if(products.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No products found");

            }
            else{
                String[] productNames = products.stream().map(Product::getName).toArray(String[]::new);
                String selected = (String) JOptionPane.showInputDialog(this,"Choose Product","Select Product",JOptionPane.QUESTION_MESSAGE,null,productNames,productNames[0]);
                if(selected!=null){
                    Product selectedProduct = products.stream().filter(product -> product.getName().equals(selected)).findFirst().orElse(null);
                    if(selectedProduct!=null){
                        try{
                            String qtyString = JOptionPane.showInputDialog("Enter Quantity");
                            if(qtyString!=null){
                                Integer qty = Integer.parseInt(qtyString);
                                if(qty<=0){
                                    JOptionPane.showMessageDialog(this, "Quantity should be greater than 0");
                                }
                                else{
                                    Sale sale = new Sale(selectedProduct.getId(),qty, LocalDateTime.now());
                                    salesDAO.InsertSale(sale);
                                    loadSales();
                                }

                            }

                        }catch (NumberFormatException ex)
                        {
                            JOptionPane.showMessageDialog(this,"Invalid Quantity");
                        }
                    }
                }
            }
        });

        removeButton.addActionListener(e -> {
            ProductDAO productDAO = new ProductDAO();
           int row = table.getSelectedRow();
           if (row >= 0) {
               int saleID = Integer.parseInt(model.getValueAt(row, 0).toString());
               int productID = productDAO.getProductIdByName((model.getValueAt(row, 1).toString()));
               int quantity = Integer.parseInt(model.getValueAt(row, 2).toString());
               Product p = productDAO.getProductById(productID);
               productDAO.UpdateProduct(new Product(p.getId(),p.getName(),p.getPrice(),p.getQuantity()+quantity));
               salesDAO.DeleteSale(new Sale(saleID,0,0,null));
               loadSales();

           }
           else {
               JOptionPane.showMessageDialog(this, "Please select a row");
           }
        });

    }

    private void loadSales()
    {
        model.setRowCount(0);
        List<Sale> sales = salesDAO.GetAllSales();
        for (Sale sale : sales) {
            Product p = productDAO.getProductById(sale.getProductId());
            String name = (p!=null?p.getName(): "Unknown");
            model.addRow(new Object[]{sale.getId(),name,sale.getQuantitySold(),sale.getSaleDate().toString()});
        }
    }

}
