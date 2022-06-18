package pl.akepa.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderingTest {

    private List<ProductDetails> products;

    @BeforeEach
    void setUp() {
        products = Collections.emptyList();
    }

    @Test
    void itAllowsToOrderCollectedProducts() {
        String productId = thereIsExampleProduct();
        Sales sales = thereIsSalesModule();
        String customerId = thereIsCustomer();
        sales.addToCart(customerId, productId);
        Offer seenOffer = sales.getCurrentOffer(customerId);
        //when

        PaymentData payment = sales.acceptOffer(
                customerId,
                seenOffer,
                getExampleClientData());
        //paymentUrl

        assertNotNull(payment.getUrl());
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
                new ListProductDetailsProvider(products)
        );
    }

    private String thereIsCustomer() {
        return "Alicja";
    }

    private ClientData getExampleClientData() {
        return ClientData.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john@doe.pl")
                .build();
    }
}
