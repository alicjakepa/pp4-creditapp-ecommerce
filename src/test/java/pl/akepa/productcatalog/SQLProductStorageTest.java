package pl.akepa.productcatalog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SQLProductStorageTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void example() {
        jdbcTemplate.execute("Select NOW(), VERSION()");
    }
    @Test
    void itAllowsToStoreAndLoadProduct() {
        ProductData product = thereIsExampleProduct();
        ProductStorage listProductStorage = thereIsSQLProductStorage();

        listProductStorage.save(product);
        ProductData loaded = listProductStorage.load(product.getId());

        assertEquals(product.getId(), loaded.getId());
        assertEquals(product.getName(), loaded.getName());
    }

    private ProductStorage thereIsSQLProductStorage() {
        return new SQLProductStorage(jdbcTemplate);
    }

    private ProductData thereIsExampleProduct() {
        return new ProductData("lego", "Nice One");
    }
}