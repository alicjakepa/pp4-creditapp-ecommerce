package pl.akepa.sales;

import org.assertj.core.util.BigDecimalComparator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CollectingProductsTest {

    @Test
    void allowsToAddProduct() {
        String productId = thereIsProduct("lego", BigDecimal.TEN);
        String customerId = thereIsCustomer();
        Sales sales = thereIsSalesModule();

        sales.addToCart(customerId, productId);

        thereIsXProductsInCustomersCart(customerId, 1);
    }

    @Test
    void itPresentsCurrentOffer() {
        String customerId = thereIsCustomer();
        Sales sales = thereIsSalesModule();

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(BigDecimal.ZERO, offer.getTotal());
        assertEquals(0, offer.getItemsCount());
    }

    @Test
    void itAllowsToAddMultipleProductsToCart() {
        String customerId = thereIsCustomer();
        String productId1 = thereIsProduct("lego-1", BigDecimal.valueOf(10.0));
        String productId2 = thereIsProduct("lego-2", BigDecimal.valueOf(20.0));
        Sales sales = thereIsSalesModule();

        sales.addToCart(customerId, productId1);
        sales.addToCart(customerId, productId2);

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(BigDecimal.valueOf(20.00), offer.getTotal());
        assertEquals(2, offer.getItemsCount());
    }

    private String thereIsCustomer() {
        return UUID.randomUUID().toString();
    }

    private void thereIsXProductsInCustomersCart(String customerId, int expectedItemsCount) {

    }

    private Sales thereIsSalesModule() {
        return new Sales();
    }

    private String thereIsProduct(String id, BigDecimal price) {
        return id;
    }

}
