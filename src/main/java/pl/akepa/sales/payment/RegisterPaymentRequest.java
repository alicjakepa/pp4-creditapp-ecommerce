package pl.akepa.sales.payment;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RegisterPaymentRequest {
    String reservationId;
    BigDecimal total;
    String firstname;
    String lastname;
    String email;

    public int totalAsInt() {
        return total.multiply(BigDecimal.valueOf(100)).intValue();
    }
}
