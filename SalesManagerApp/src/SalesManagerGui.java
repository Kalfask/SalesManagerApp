import javax.swing.*;
import java.awt.*;

public class SalesManagerGui extends JFrame {

    public SalesManagerGui() {

        setTitle("Sales Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Products", new ProductPanel());
        tabbedPane.addTab("Sales", new SalesPanel());
        tabbedPane.addTab("Reports", new ReportPanel());

        add(tabbedPane);

    }

    private JPanel createProductPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Product management will be here"),BorderLayout.CENTER);
        return panel;
    }

    private JPanel createSalesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Sales registration will be here."), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createReportPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Sales reports will be here."), BorderLayout.CENTER);
        return panel;
    }

}
