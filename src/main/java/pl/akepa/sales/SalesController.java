package pl.akepa.sales;

import org.springframework.web.bind.annotation.GetMapping;
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

    private String getCurrentCustomerId() {
        return CUSTOMER_ID;
    }
}
