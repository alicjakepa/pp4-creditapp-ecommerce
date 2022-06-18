package pl.akepa.payu;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PayUTest {

    @Test
    void itRegisterExamplePayment() {
        PayU payu = thereIsPayUApiClient();
        String reservationId = thereIsExampleReservation();
        OrderCreateRequest request = thereIsExampleOrderCreateRequest();

        OrderCreateResponse response = payu.handle(request);

        assertEquals("SUCCESS", response.getStatus().getStatusCode());
        assertNotNull(response.getOrderId());
        assertNotNull(response.getRedirectUri());
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {
//        -d '{
//        "notifyUrl": "https://your.eshop.com/notify",
//                "customerIp": "127.0.0.1",
//                "merchantPosId": "300746",
//                "description": "RTV market",
//                "currencyCode": "PLN",
//                "totalAmount": "21000",
//                "buyer": {
//            "email": "john.doe@example.com",
//                    "phone": "654111654",
//                    "firstName": "John",
//                    "lastName": "Doe",
//                    "language": "pl"
//        },
//        "products": [
//        {
//            "name": "Wireless Mouse for Laptop",
//                "unitPrice": "15000",
//                "quantity": "1"
//        },
//        {
//            "name": "HDMI cable",
//                "unitPrice": "6000",
//                "quantity": "1"
//        }
//        ]
//    }'

//        "notifyUrl": "https://your.eshop.com/notify",
//                "customerIp": "127.0.0.1",
//                "merchantPosId": "300746",
//                "description": "RTV market",
//                "currencyCode": "PLN",
//                "totalAmount": "21000",
        return OrderCreateRequest.builder()
                .notifyUrl("https://your.eshop.com/notify")
                .customerIp("127.0.0.1")
                .description("RTV market")
                .currencyCode("PLN")
                .totalAmount(21000)
                .buyer(Buyer.builder()
                        .firstName("john")
                        .lastName("doe")
                        .email("john@doe.pl")
                        .phone("0202122")
                        .language("PL")
                        .build())
                .products(Arrays.asList(
                        Product.builder()
                                .name("product 1")
                                .quantity(1)
                                .unitPrice(21000)
                                .build()
                ))
                .build();
    }

    private String thereIsExampleReservation() {
        return UUID.randomUUID().toString();
    }

    private PayU thereIsPayUApiClient() {
        return new PayU("300746");
    }
}
