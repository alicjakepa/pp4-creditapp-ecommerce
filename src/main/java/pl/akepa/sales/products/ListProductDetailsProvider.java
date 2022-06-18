package pl.akepa.sales.products;

import java.util.List;
import java.util.Optional;

public class ListProductDetailsProvider
        implements ProductDetailsProvider {

    List<ProductDetails> productDetails;

    public ListProductDetailsProvider(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }

    @Override
    public Optional<ProductDetails> findById(String productId) {
        return productDetails.stream()
                .filter(p -> p.productId.equals(productId))
                .findFirst();
    }
}
