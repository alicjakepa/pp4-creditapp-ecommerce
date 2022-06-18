package pl.akepa.productcatalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {

    @Test
    void itAllowToListOnlyPublishedProducts() {
        ProductCatalog catalog = thereIsProductCatalog();
        List<ProductData> products = catalog.allPublishedProducts();
        assertEquals(0, products.size());
    }

    @Test
    void itAllowsToAddProductDraft() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego-set", "Nice one");

        List<ProductData> products = catalog.allPublishedProducts();
        assertEquals(0, products.size());
    }

    @Test
    void itDenyToPublishProductWithoutPrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego-set", "Nice one");

        assertThrows(CantPublishProductException.class, () -> {
            catalog.publish(productId);
        });
    }

    @Test
    void itAllowsToAssignPrice() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego-set", "Nice one");
        catalog.assignPrice(productId, BigDecimal.valueOf(10.10));

        ProductData loaded = catalog.getDetails(productId);
        assertEquals(BigDecimal.valueOf(10.10), loaded.getPrice());
    }

    @Test
    void itDenyToPublishProductWithoutImage() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego-set", "Nice one");
        catalog.assignPrice(productId, BigDecimal.valueOf(10.10));

        assertThrows(CantPublishProductException.class, () -> {
            catalog.publish(productId);
        });
    }

    @Test
    void itAllowsToListPublishedProduct() {
        ProductCatalog catalog = thereIsProductCatalog();
        String productId = catalog.addProduct("lego-set", "Nice one");
        catalog.assignPrice(productId, BigDecimal.valueOf(10.10));
        catalog.assignImage(productId, "http://nicePicture");

        catalog.publish(productId);

        List<ProductData> products = catalog.allPublishedProducts();
        assertEquals(1, products.size());
    }



    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog(new MapProductStorage());
    }
}
