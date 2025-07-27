# SalesManagerApp
Sales Manager is a desktop application built with Java Swing and SQLite that allows businesses to manage products, record sales, and generate basic reports. The system is designed with simplicity, clarity, and extensibility in mind, making it ideal for small shops or educational purposes.

💡 Core Features
📦 Product Management
Add new products with name, price, and available quantity.

View all existing products in a table.

Update or delete products from the database.

Real-time search for specific products.

💰 Sales Management
Record a sale by selecting a product and specifying the quantity sold.

Automatically updates the product's stock after each sale.

Prevents sales if stock is insufficient.

View and delete recorded sales.

When a sale is deleted, the stock is restored accordingly.

📊 Reporting
View all recorded sales in a table.

Shows sale ID, product name, quantity sold, and sale date.

Calculates and displays total revenue from all sales.

🧱 Architecture
Database Layer:
Uses SQLite to persist data locally. Tables:

products(id, name, price, quantity)

sales(id, product_id, quantity_sold, sale_date)

DAO Classes:

ProductDAO: Handles product-related database operations.

SalesDAO: Handles sales-related database operations.

Model Classes:

Product: Represents a product entity.

Sale: Represents a sale entity.

GUI:
Built with JFrame, JTabbedPane, JTable, JButton, and JOptionPane.

Tabs for Products, Sales, and Reports.

Simple, user-friendly interface.

⚙️ Technologies Used
Java (JDK 17+)

Swing (for GUI)

SQLite (embedded DB)

Maven/IntelliJ IDEA (recommended IDEs)

🎯 Target Audience
This project is ideal for:

Small businesses that need a lightweight sales tracker.

Students learning Java, databases, and GUI development.

Anyone interested in combining CRUD operations with real-world interfaces.

📈 Possible Extensions
User authentication system.

Exporting reports as CSV or PDF.

Chart-based visual reports (e.g., bar/line charts).

Stock alerts for low-quantity products.

Integration with online databases or cloud storage.
