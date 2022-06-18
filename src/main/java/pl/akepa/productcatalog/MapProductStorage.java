package pl.akepa.productcatalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MapProductStorage implements ProductStorage {
    /// Storage
    Map<String, ProductData> products;

    public MapProductStorage() {
        this.products = new HashMap<>();
    }

    @Override
    public void save(ProductData newProduct) {
        products.put(newProduct.getId(), newProduct);
    }

    @Override
    public ProductData load(String productId) {
        return products.get(productId);
    }

    @Override
    public List<ProductData> allPublished() {
        return products.values()
                .stream()
                .filter(productData -> productData.isOnline())
                .collect(Collectors.toList());
    }
}
