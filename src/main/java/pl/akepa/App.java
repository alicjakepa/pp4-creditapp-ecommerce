package pl.akepa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.akepa.creditcard.NameProvider;
import pl.akepa.payu.PayU;
import pl.akepa.productcatalog.MapProductStorage;
import pl.akepa.productcatalog.ProductCatalog;
import pl.akepa.productcatalog.ProductData;
import pl.akepa.productcatalog.ProductStorage;
import pl.akepa.sales.*;
import pl.akepa.sales.cart.CartStorage;
import pl.akepa.sales.payment.PayUPaymentGateway;
import pl.akepa.sales.payment.PaymentGateway;
import pl.akepa.sales.products.ProductDetails;
import pl.akepa.sales.products.ProductDetailsProvider;
import pl.akepa.sales.reservation.ReservationStorage;

import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

    }

    @Bean
    NameProvider createNameProvider() {
        return new NameProvider();
    }

    @Bean
    ProductStorage createMyProductStorage() {
            return new MapProductStorage();
    }

    @Bean
    ProductCatalog createMyProductCatalog(ProductStorage productStorage) {
        ProductCatalog productCatalog = new ProductCatalog(productStorage);
        String productId1 = productCatalog.addProduct("lego-set-1", "Nice Lego set");
        productCatalog.assignImage(productId1, "https://picsum.photos/id/237/200/300");
        productCatalog.assignPrice(productId1, BigDecimal.TEN);
        productCatalog.publish(productId1);

        String productId2 = productCatalog.addProduct("lego-set-2", "Even nicer Lego set");
        productCatalog.assignImage(productId2, "https://picsum.photos/id/238/200/300");
        productCatalog.assignPrice(productId2, BigDecimal.valueOf(20.20));
        productCatalog.publish(productId2);

        return productCatalog;
    }

    @Bean
    PaymentGateway createPaymentGateway() {
        return new PayUPaymentGateway(
                new PayU(System.getenv("PAYU_MERCHANT_POS_ID")));

    }

    @Bean
    Sales createSales(ProductDetailsProvider productDetailsProvider, PaymentGateway paymentGateway) {
        return new Sales(
                new CartStorage(),
                productDetailsProvider,
                paymentGateway,
                new ReservationStorage()
        );
    }

    @Bean
    ProductDetailsProvider detailsProvider(ProductCatalog catalog) {
        return (productId -> {
            ProductData data = catalog.getDetails(productId);
            return java.util.Optional.of(new ProductDetails(
                    data.getId(),
                    data.getName(),
                    data.getPrice()));
        });
    }

}
