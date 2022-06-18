package pl.akepa.sales;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalesController {
    public static final String CUSTOMER_ID = "KUBA";

    Sales sales;

    public SalesController(Sales sales) {
        this.sales = sales;
    }

    @GetMapping("/api/sales/offer")
    Offer currentOffer() {
        return sales.getCurrentOffer(getCurrentCustomerId());
    }

    @PostMapping("/api/sales/add-product/{productId}")
    void addToCart(@PathVariable String productId) {
        sales.addToCart(getCurrentCustomerId(), productId);
    }

    private String getCurrentCustomerId() {
        return CUSTOMER_ID;
    }
}
