package pl.akepa.sales;

import java.math.BigDecimal;

public class Sales {

    CartStorage cartStorage;
    ProductDetailsProvider productDetailsProvider;

    public Sales(CartStorage cartStorage, ProductDetailsProvider productDetailsProvider) {
        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetailsProvider;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());

        BigDecimal total = cart
                .getItems()
                .stream()
                .map(this::calculateLineTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return Offer.of(total, cart.items.size());
    }

    private BigDecimal calculateLineTotal(CartItem cartItem) {
        return cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
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

    public PaymentData acceptOffer(String customerId, Offer seenOffer, ClientData clientData) {
        return null;
    }
}
