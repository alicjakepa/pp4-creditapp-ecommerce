package pl.akepa.payu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    String name;
    Integer unitPrice;
    Integer quantity;
//    "name": "Wireless Mouse for Laptop",
//            "unitPrice": "15000",
//            "quantity": "1"
}
