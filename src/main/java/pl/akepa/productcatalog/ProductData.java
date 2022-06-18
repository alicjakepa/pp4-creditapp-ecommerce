package pl.akepa.productcatalog;

import java.math.BigDecimal;

public class ProductData {
    private final String id;
    private final String name;
    private BigDecimal newPrice;
    private String image;
    private boolean online;

    public ProductData(String productId, String name) {
        this.id = productId;
        this.name = name;
    }

    public BigDecimal getPrice() {
        return newPrice;
    }

    public void changePrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public String getImage() {
        return image;
    }

    public void assignImage(String newImage) {

        this.image = newImage;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setOnline(boolean online) {

        this.online = online;
    }

    public boolean isOnline() {
        return online;
    }
}