package pl.akepa.sales.payment;

public interface PaymentGateway {

    RegisterPaymentResponse handle(RegisterPaymentRequest registerPaymentRequest);
}
