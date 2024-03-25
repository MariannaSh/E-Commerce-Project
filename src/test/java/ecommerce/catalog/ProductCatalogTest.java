package ecommerce.catalog;


import org.junit.jupiter.api.Test;
import pl.shcherba.ecommerce.catalog.Product;
import pl.shcherba.ecommerce.catalog.ProductCatalog;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalogTest {

    @Test
    void itListAvailableProducts() {
        ProductCatalog catalog = new ProductCatalog();

        List<Product> products = catalog.allProducts();

        assert products.isEmpty();
    }

    @Test
    void itListAllowsToAddProduct() {
        ProductCatalog catalog = new ProductCatalog();

        catalog.addProduct("Lego set 8083", "Nice one");
        List<Product> products = catalog.allProducts();

        Assertions.assertThat(products)
                .hasSize(1);

    }

    @Test
    void  itLoadsSingleProductById() {
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8083", "Nice one");

        Product loaded = catalog.getProductBy(id);

        Assertions.assertThat(id).isEqualTo(loaded.getId());
    }

    @Test
    void itAllowsChangePrice() {
        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 8083", "Nice one");


        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductBy(id);

        Assertions.assertThat(BigDecimal.valueOf(10.10)).isEqualTo(loaded.getPrice());
    }

}
