package pl.akepa.productcatalog;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductCatalog {
    Map<String, ProductData> products;
    public ProductCatalog() {
        this.products = new HashMap<>();
    }

    public String addProduct(String productId, String name) {
        ProductData newProduct = new ProductData(productId, name);
        products.put(productId, newProduct);

        return productId;
    }

    private ProductData loadProductById(String productId) {
        return products.get(productId);
    }

    public void publish(String productId) {
        ProductData loaded = loadProductById(productId);

        if (loaded.getPrice() == null) {
            throw new CantPublishProductException();
        }

        if (loaded.getImage() == null) {
            throw new CantPublishProductException();
        }
    }

    public List<ProductData> allPublishedProducts() {
        return Collections.emptyList();
    }

    public void assignPrice(String productId, BigDecimal newPrice) {
        ProductData loaded = loadProductById(productId);
        loaded.changePrice(newPrice);
    }

    public ProductData getDetails(String productId) {
        return loadProductById(productId);
    }

    public void assignImage(String productId, String s) {

    }
}
