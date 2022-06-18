package pl.akepa.sales;

import org.springframework.web.bind.annotation.*;
import pl.akepa.sales.offer.Offer;
import pl.akepa.sales.payment.PaymentData;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class SalesController {
    public static final String CUSTOMER_ID = "KUBA";

    Sales sales;
    private HttpSession httpSession;

    public SalesController(Sales sales, HttpSession httpSession) {
        this.sales = sales;
        this.httpSession = httpSession;
    }

    @GetMapping("/api/sales/offer")
    Offer currentOffer() {
        return sales.getCurrentOffer(getCurrentCustomerId());
    }

    @PostMapping("/api/sales/add-product/{productId}")
    void addToCart(@PathVariable String productId) {
        sales.addToCart(getCurrentCustomerId(), productId);
    }

    @PostMapping("/api/sales/accept-offer")
    PaymentData acceptOffer(@RequestBody ClientData clientData) {
        //when //act
        PaymentData payment = sales.acceptOffer(
                getCurrentCustomerId(),
                clientData
        );

        return payment;
    }

    private String getCurrentCustomerId() {
        Object customerId = httpSession.getAttribute("CUSTOMER_ID");

        if (customerId == null) {
            customerId = UUID.randomUUID().toString();
            httpSession.setAttribute("CUSTOMER_ID", customerId);
        }
        return (String) customerId;
    }
}
