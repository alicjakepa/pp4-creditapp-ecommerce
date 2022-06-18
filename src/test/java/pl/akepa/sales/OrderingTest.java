package pl.akepa.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.akepa.sales.cart.CartStorage;
import pl.akepa.sales.offer.Offer;
import pl.akepa.sales.offer.OfferNotMatchedException;
import pl.akepa.sales.payment.DummyPaymentGateway;
import pl.akepa.sales.payment.PaymentData;
import pl.akepa.sales.products.ListProductDetailsProvider;
import pl.akepa.sales.products.ProductDetails;
import pl.akepa.sales.reservation.Reservation;
import pl.akepa.sales.reservation.ReservationStorage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OrderingTest {

    private List<ProductDetails> products;
    private ReservationStorage reservationStorage;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        reservationStorage = new ReservationStorage();
    }

    @Test
    void itAllowsToOrderCollectedProducts() {
        //Given // Arrange
        String productId = thereIsExampleProduct();
        Sales sales = thereIsSalesModule();
        String customerId = thereIsCustomer();
        sales.addToCart(customerId, productId);
        Offer seenOffer = sales.getCurrentOffer(customerId);

        //when //act
        PaymentData payment = sales.acceptOffer(customerId, exampleCustomerData());

        //then // assert
        String reservationId = payment.getReservationId();

        assertNotNull(payment.getUrl());
        assertNotNull(reservationId);
        thereIsPendingReservationWithId(reservationId);
    }

    private void thereIsPendingReservationWithId(String reservationId) {
        Optional<Reservation> optionalReservation = reservationStorage.find(reservationId);

        assertTrue(optionalReservation.isPresent());
    }

    @Test
    void denyPurchaseWhenOfferDifferentThanSeenOffer() {
        String productId = thereIsExampleProduct();
        Sales sales = thereIsSalesModule();
        String customerId = thereIsCustomer();
        sales.addToCart(customerId, productId);
        Offer seenOffer = sales.getCurrentOffer(customerId);
        Offer newOffer = Offer.of(BigDecimal.valueOf(1000), 1);
        //when

        assertThrows(OfferNotMatchedException.class, () -> {
            sales.acceptOffer(
                    customerId,
                    exampleCustomerData());
        });
    }

    private String thereIsExampleProduct() {
        String productId = "123";
        products.add(new ProductDetails(
                productId,
                "Lego",
                BigDecimal.valueOf(10.10)));
        return productId;
    }

    private Sales thereIsSalesModule() {
        return new Sales(
                new CartStorage(),
                new ListProductDetailsProvider(products),
                new DummyPaymentGateway(),
                reservationStorage
        );
    }

    private String thereIsCustomer() {
        return "Alicja";
    }

    private ClientData exampleCustomerData() {
        return ClientData.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john@doe.pl")
                .build();
    }
}
