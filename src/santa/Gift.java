package santa;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Gift {
    private final String productName;
    private final double price;
    private final String category;
    @JsonIgnore
    private Integer quantity;

    public Gift(final String productName, final double price,
                final String category, final Integer quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
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

    /**
     * checkstyle
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * checkstyle
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }
}
