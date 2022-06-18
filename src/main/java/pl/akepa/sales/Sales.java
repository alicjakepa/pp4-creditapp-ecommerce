package pl.akepa.sales;

public class Sales {

    CartStorage cartStorage;
    ProductDetailsProvider productDetailsProvider;

    public Sales(CartStorage cartStorage, ProductDetailsProvider productDetailsProvider) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetailsProvider;
    }

    public Offer getCurrentOffer(String customerId) {
        return Offer.emptyOffer();
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());

        ProductDetails productDetails = productDetailsProvider.findById(productId)
                .orElseThrow(() -> new ProductNotAvailableException());

        cart.addItem(CartItem.of(
                productId,
                productDetails.name,
                productDetails.price));

        cartStorage.save(customerId, cart);
    }
}
