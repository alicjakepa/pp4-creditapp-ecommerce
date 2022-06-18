package pl.akepa.productcatalog;

import java.math.BigDecimal;

public class ProductData {
    private final String productId;
    private final String name;
    private BigDecimal newPrice;

    public ProductData(String productId, String name) {
        this.productId = productId;
        this.name = name;
    }

    public BigDecimal getPrice() {
        return newPrice;
    }

    public void changePrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public String getImage() {
        return null;
    }
}
