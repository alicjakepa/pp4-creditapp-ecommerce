package pl.akepa.sales.payment;

public class DummyPaymentGateway implements PaymentGateway {
    public RegisterPaymentResponse handle(RegisterPaymentRequest registerPaymentRequest) {
        return new RegisterPaymentResponse(
                "dummyId",
                "https://gateway/url");
    }
}
