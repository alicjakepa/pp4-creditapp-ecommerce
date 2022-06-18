package pl.akepa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.akepa.creditcard.NameProvider;
import pl.akepa.productcatalog.MapProductStorage;
import pl.akepa.productcatalog.ProductCatalog;
import pl.akepa.productcatalog.ProductStorage;
import pl.akepa.sales.CartStorage;
import pl.akepa.sales.ProductDetailsProvider;
import pl.akepa.sales.Sales;

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
    Sales createSales() {
        return new Sales(new CartStorage(), new ProductDetailsProvider());
    }

}
