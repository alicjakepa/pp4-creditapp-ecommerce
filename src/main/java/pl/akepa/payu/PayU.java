package pl.akepa.payu;

import org.springframework.web.client.RestTemplate;

import java.time.Duration;

public class PayU {
    private final RestTemplate springHttp;

    private String merchantPosId;

    public PayU(String merchantPosId) {
        this.merchantPosId = merchantPosId;


        this.springHttp = new RestTemplate();
    }

    public OrderCreateResponse handle(
            OrderCreateRequest request) {

        request.setMerchantPosId(merchantPosId);

        OrderCreateResponse r =springHttp
                .postForObject(
                        "https://secure.snd.payu.com/api/v2_1/orders",
                        request,
                        OrderCreateResponse.class);

        //CURL -X POST



        return null;
    }
}
