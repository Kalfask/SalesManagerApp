import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.List;

public class ProductPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;
    private ProductDAO productDAO = new ProductDAO();
    private JTextField searchField;

    public ProductPanel() {
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        topPanel.add(new JLabel("Search Product:"),BorderLayout.WEST);
        topPanel.add(searchField,BorderLayout.CENTER);
        add(topPanel,BorderLayout.NORTH);


        tableModel = new DefaultTableModel(new Object[]{"ID","Name","Price","Quantity"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane,BorderLayout.CENTER);



        JPanel bottomPanel = new JPanel();
        JButton resetButton = new JButton("Reset");
        JButton addButton = new JButton("Add Product");
        JButton deleteButton = new JButton("Delete Product");
        JButton updateButton = new JButton("Update Product");
        bottomPanel.add(resetButton);
        bottomPanel.add(addButton);
        bottomPanel.add(deleteButton);
        bottomPanel.add(updateButton);
        add(bottomPanel,BorderLayout.SOUTH);


        loadProducts("");
        addButton.addActionListener(e -> {
           String name = JOptionPane.showInputDialog("Enter Product Name:");
           double price = Double.parseDouble(JOptionPane.showInputDialog("Enter Product Price:"));
           int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter Product Quantity:"));

           productDAO.addProduct(new Product(name,price,quantity));
           loadProducts("");

        });

        deleteButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(table.getValueAt(row,0).toString());
                productDAO.DeleteProduct(id);
                loadProducts("");
            }
            else {
                JOptionPane.showMessageDialog(null, "Please Select a Row!");
            }
        });

        updateButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = Integer.parseInt(table.getValueAt(row,0).toString());
                String name = JOptionPane.showInputDialog("Enter New Product Name:");
                double price = Double.parseDouble(JOptionPane.showInputDialog("Enter New Product Price:"));
                int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter New Product Quantity:"));
                productDAO.UpdateProduct(new Product(id,name,price,quantity));
                loadProducts("");
            }
            else  {
                JOptionPane.showMessageDialog(null, "Please Select a Row!");
            }
        });



        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                loadProducts(searchField.getText());
            }
        });

        resetButton.addActionListener(e -> {
            loadProducts("");
        });

    }

    public void loadProducts(String keyword){
        tableModel.setRowCount(0);
        List<Product> products = productDAO.getAllProducts();
        for (Product product : products) {
            if(keyword.isEmpty() || product.getName().toLowerCase().contains(keyword.toLowerCase())){
                tableModel.addRow(new Object[]{product.getId(),product.getName(),product.getPrice(),product.getQuantity()});
            }
        }


    }

}

