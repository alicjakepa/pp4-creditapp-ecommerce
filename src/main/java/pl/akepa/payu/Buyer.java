package pl.akepa.payu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Buyer {
    String email;
    String phone;
    String firstName;
    String lastName;
    String language;
//    "email": "john.doe@example.com",
//                "phone": "654111654",
//                "firstName": "John",
//                "lastName": "Doe",
//                "language": "pl"
}
