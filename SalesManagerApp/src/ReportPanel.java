import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReportPanel extends JPanel {

    private DefaultTableModel reportModel;
    private JLabel totalLabel;


    public ReportPanel() {
        setLayout(new BorderLayout());


        String[] collumNames = {"Sale ID", "Product", "Quantity", "Date", "Total (€)"};
        reportModel = new DefaultTableModel(collumNames, 0);
        JTable reportTable = new JTable(reportModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);

        JButton loadButton = new JButton("Load");
        totalLabel = new JLabel("Total Revenue: 0.00€");

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(loadButton, BorderLayout.WEST);
        topPanel.add(totalLabel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);


        loadButton.addActionListener(e -> loadReports());


    }

    private void loadReports()
    {
        reportModel.setRowCount(0);

        SalesDAO salesDAO = new SalesDAO();
        ProductDAO productDAO = new ProductDAO();

        List<Sale> sales = salesDAO.GetAllSales();
        double salesTotal = 0;
        for (Sale sale : sales) {
            Product p = productDAO.getProductById(sale.getProductId());
            if(p != null)
            {
                double total = p.getPrice()*sale.getQuantitySold();
                salesTotal += total;
                reportModel.addRow(new Object[]{
                        sale.getProductId(),
                        p.getName(),
                        sale.getQuantitySold(),
                        sale.getSaleDate().toString(),
                        String.format("%.2f", total)
                });
            }
        }
            totalLabel.setText("Total Revenue: " + salesTotal);
    }
}
