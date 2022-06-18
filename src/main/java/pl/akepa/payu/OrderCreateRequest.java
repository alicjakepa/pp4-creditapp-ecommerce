package pl.akepa.payu;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderCreateRequest {
    //    "notifyUrl": "https://your.eshop.com/notify",
//            "customerIp": "127.0.0.1",
//            "merchantPosId": "300746",
//            "description": "RTV market",
//            "currencyCode": "PLN",
//            "totalAmount": "21000",
//            "buyer": {
//        "email": "john.doe@example.com",
//                "phone": "654111654",
//                "firstName": "John",
//                "lastName": "Doe",
//                "language": "pl"
//    },
    String notifyUrl;
    String customerIp;
    String merchantPosId;
    String description;
    String currencyCode;
    Integer totalAmount;
    Buyer buyer;
    List<Product> products;
}
