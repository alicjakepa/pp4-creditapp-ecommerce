package pl.akepa.productcatalog;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class ProductData {
    @Id
    private String id;
    private String name;
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