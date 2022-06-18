package pl.akepa.sales.payment;

import pl.akepa.payu.*;

import java.util.Arrays;

public class PayUPaymentGateway implements PaymentGateway {

    PayU payU;

    public PayUPaymentGateway(PayU payU) {
        this.payU = payU;
    }

    @Override
    public RegisterPaymentResponse handle(RegisterPaymentRequest registerPaymentRequest) {

        OrderCreateResponse response =payU.handle(OrderCreateRequest.builder()
                .notifyUrl("https://your.eshop.com/notify")
                .customerIp("127.0.0.1")
                .description("Moja usługa")
                .currencyCode("PLN")
                .totalAmount(registerPaymentRequest.totalAsInt())
                .buyer(Buyer.builder()
                        .firstName(registerPaymentRequest.firstname)
                        .lastName(registerPaymentRequest.lastname)
                        .email(registerPaymentRequest.email)
                        .language("pl")
                        .build())
                .products(Arrays.asList(
                        Product.builder()
                                .name("Moja usługa")
                                .quantity(1)
                                .unitPrice(registerPaymentRequest.totalAsInt())
                                .build()
                ))
                .build());

        return new RegisterPaymentResponse(
                response.getOrderId(),
                response.getRedirectUri());
    }
}
