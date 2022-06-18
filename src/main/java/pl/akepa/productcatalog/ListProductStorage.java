package pl.akepa.productcatalog;

import java.util.List;

public class ListProductStorage implements ProductStorage {
    @Override
    public void save(ProductData newProduct) {

    }

    @Override
    public ProductData load(String productId) {
        return null;
    }

    @Override
    public List<ProductData> allPublished() {
        return null;
    }
}
