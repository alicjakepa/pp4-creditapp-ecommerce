package pl.akepa.sales;

import pl.akepa.sales.cart.Cart;
import pl.akepa.sales.cart.CartItem;
import pl.akepa.sales.cart.CartStorage;
import pl.akepa.sales.offer.Offer;
import pl.akepa.sales.offer.OfferMaker;
import pl.akepa.sales.payment.PaymentData;
import pl.akepa.sales.payment.PaymentGateway;
import pl.akepa.sales.products.ProductDetails;
import pl.akepa.sales.products.ProductDetailsProvider;
import pl.akepa.sales.products.ProductNotAvailableException;
import pl.akepa.sales.reservation.Reservation;
import pl.akepa.sales.reservation.ReservationStorage;

import java.util.UUID;

public class Sales {

    CartStorage cartStorage;
    ProductDetailsProvider productDetailsProvider;
    PaymentGateway paymentGateway;
    ReservationStorage reservationStorage;

    public Sales(CartStorage cartStorage,
                 ProductDetailsProvider productDetailsProvider,
            PaymentGateway paymentGateway,
                 ReservationStorage reservationStorage) {

        this.cartStorage = cartStorage;
        this.productDetailsProvider = productDetailsProvider;
        this.paymentGateway = paymentGateway;
        this.reservationStorage = reservationStorage;
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());
        return calculateOffer(cart);
    }

    private Offer calculateOffer(Cart cart) {
        OfferMaker offerMaker = new OfferMaker();
        return offerMaker.calculateOffer(cart);
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());

        ProductDetails productDetails = productDetailsProvider.findById(productId)
                .orElseThrow(() -> new ProductNotAvailableException());

        cart.addItem(CartItem.of(
                productId,
                productDetails.getName(),
                productDetails.getPrice()));

        cartStorage.save(customerId, cart);
    }

    public PaymentData acceptOffer(String customerId, ClientData clientData) {
        Cart cart = cartStorage.getForCustomer(customerId)
                .orElse(Cart.empty());

        Offer currentOffer = calculateOffer(cart);
        String id = UUID.randomUUID().toString();
        Reservation reservation = Reservation.of(
                id,
                currentOffer.getTotal(),
                clientData
        );

        PaymentData paymentData = reservation
                .registerPayment(paymentGateway);

        reservationStorage.save(reservation);

        return paymentData;
    }
}
