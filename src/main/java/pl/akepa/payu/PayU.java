package pl.akepa.payu;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PayU {
    private final RestTemplate http;

    private String merchantPosId;

    public PayU(String merchantPosId) {
        this.merchantPosId = merchantPosId;
        this.http = new RestTemplate();
    }

    public OrderCreateResponse handle(
            OrderCreateRequest request) {

        request.setMerchantPosId(merchantPosId);

        String url = "https://secure.snd.payu.com/api/v2_1/orders";
        String token = getAuthToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(
                "Authorization",
                String.format("Bearer %s", token));

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrderCreateRequest> httpRequest = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<OrderCreateResponse> r = http.postForEntity(url, httpRequest, OrderCreateResponse.class);

        return r.getBody();
    }

    private String getAuthToken() {
        return "d9a4536e-62ba-4f60-8017-6053211d3f47";
        //grant_type=client_credentials
        // &client_id=145227&
        // client_secret=12f071174cb7eb79d4aac5bc2f07563f

//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<String> httpEntity = new HttpEntity<String>(
//                "grant_type=client_credentials&client_id=145227&client_secret=12f071174cb7eb79d4aac5bc2f07563f",
//                headers
//        );
//
//        AccessTokenResponse response = http.postForObject(
//                "https://secure.payu.com/pl/standard/user/oauth/authorize",
//                httpEntity,
//                AccessTokenResponse.class
//        );
//
//        return response.getAccess_token();
    }
}
