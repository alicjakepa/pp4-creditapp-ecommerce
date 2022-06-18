package pl.akepa.sales.cart;

import java.math.BigDecimal;

public class CartItem {
    private String productId;
    private final String name;
    private final BigDecimal price;
    private Integer quantity;

    public CartItem(String productId, String name, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = 1;
    }

    public static CartItem of(String productId, String name, BigDecimal price) {
        return new CartItem(productId, name, price);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void increaseQuantity() {
        this.quantity++;
    }
}
