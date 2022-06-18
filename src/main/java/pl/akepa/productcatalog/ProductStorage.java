package pl.akepa.productcatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductStorage {
    /// Storage
    Map<String, ProductData> products;

    public ProductStorage() {
        this.products = new HashMap<>();
    }

    public void save(ProductData newProduct) {
        products.put(newProduct.getId(), newProduct);
    }

    public ProductData load(String productId) {
        return products.get(productId);
    }

    public List<ProductData> allPublished() {
        return products.values()
                .stream()
                .filter(productData -> productData.isOnline())
                .collect(Collectors.toList());
    }
}
