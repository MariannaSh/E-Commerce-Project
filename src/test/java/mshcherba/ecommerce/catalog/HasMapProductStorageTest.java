package mshcherba.ecommerce.catalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.*;

public class HasMapProductStorageTest {
    private static final String TEST_PRODUCT_NAME = "test product";

    @Test
    void itStoreNewProduct() {
        ProductStorage storage = thereIsProductStorage();
        Product product = thereIsExampleProduct();
        storage.add(product);
        List<Product> products = storage.allProducts();
        assertThat(products)
                .hasSize(1)
                .extracting(Product::getName)
                .contains("test product");
    }

    private ProductStorage thereIsProductStorage() {
        return new HashMapProductStorage();
    }


    private Product thereIsExampleProduct() {
        return new Product(UUID.randomUUID(), TEST_PRODUCT_NAME, "", BigDecimal.valueOf(100));
    }

    @Test
    void itLoadsAllProducts() {

    }
}