import java.time.LocalDateTime;

public class Sale {

    private int id;
    private int productId;
    private int quantitySold;
    private LocalDateTime saleDate;
    private double price;

    public Sale(int id, int productId, int quantitySold, LocalDateTime saleDate) {
        this.id = id;
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.saleDate = saleDate;
    }

    public Sale( int productId, int quantitySold, LocalDateTime saleDate) {
        this.productId = productId;
        this.quantitySold = quantitySold;
        this.saleDate = saleDate;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public int getProductId() {
        return productId;
    }

    public int getId() {
        return id;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sale of product #" + productId + " x" + quantitySold + " on " + saleDate;
    }
}
