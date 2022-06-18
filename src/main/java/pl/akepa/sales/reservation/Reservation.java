package pl.akepa.sales.reservation;

import pl.akepa.sales.*;
import pl.akepa.sales.payment.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Reservation {
    @Id
    private String id;
    private BigDecimal total;
    private CustomerInfo customerInfo;

    public Reservation(String reservationId, BigDecimal total, CustomerInfo customerInfo) {
        this.id = reservationId;
        this.total = total;
        this.customerInfo = customerInfo;
    }

    public static Reservation of(
            String reservationId,
            BigDecimal total,
            ClientData clientData) {
        return new Reservation(
                reservationId,
                total,
                new CustomerInfo(clientData.getFirstname(), clientData.getLastname(), clientData.getEmail())
        );
    }

    public PaymentData registerPayment(PaymentGateway paymentGateway) {

        RegisterPaymentResponse response = paymentGateway
                .handle(RegisterPaymentRequest.builder()
                        .email(customerInfo.getEmail())
                        .firstname(customerInfo.getFirstname())
                        .lastname(customerInfo.getLastname())
                        .reservationId(id)
                        .total(total)
                        .build()
                );

        return new PaymentData(response.getId(), id, response.getUrl());
    }

    public String getId() {
        return id;
    }
}
