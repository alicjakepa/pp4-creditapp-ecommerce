package pl.akepa.sales;

import lombok.*;

@Data
@Builder
public class ClientData {
    String firstname;
    String lastname;
    String email;
}
