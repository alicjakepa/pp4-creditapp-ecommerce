package pl.akepa.productcatalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SQLProductStorageTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void beforeEach() {
        jdbcTemplate.execute(
                "DROP TABLE `product_catalog__products` IF EXISTS"
        );
        jdbcTemplate.execute(
                "CREATE TABLE `product_catalog__products` (" +
                        "`id` varchar(100) NOT NULL, " +
                        "PRIMARY KEY(id)" +
                        ")"
        );
    }

    @Test
    void example() {
        String result = jdbcTemplate
                .queryForObject("Select NOW()", String.class);
    }

    @Test
    void itCountsProducts() {
        int productsCount = jdbcTemplate
                .queryForObject(
                        "select " +
                                "count(*) " +
                                "from " +
                                "`product_catalog__products`"
                        , Integer.class);

        assertEquals(0, productsCount);
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