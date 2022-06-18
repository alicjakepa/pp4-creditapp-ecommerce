package pl.akepa.sales;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfferHTTPTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Test
    void itAllowsToAskForOffer() {
        String url = String.format(
                "http://localhost:%s/api/sales/offer", port);

        ResponseEntity<Offer> response = http.getForEntity(
                url, Offer.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
