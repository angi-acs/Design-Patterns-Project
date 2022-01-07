package santa;


public class Gift {
    private final String productName;
    private final double price;
    private final String category;

    public Gift(final String productName, final double price,
                final String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    /**
     * checkstyle
     */
    public String getProductName() {
        return productName;
    }

    /**
     * checkstyle
     */
    public double getPrice() {
        return price;
    }

    /**
     * checkstyle
     */
    public String getCategory() {
        return category;
    }
}
