package shcherba.ecommerce.catalog;

import mshcherba.ecommerce.catalog.Product;
import mshcherba.ecommerce.catalog.ProductCatalog;
import org.junit.jupiter.api.Test;
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
    void itAllowsToAddProduct() {

        ProductCatalog catalog = new ProductCatalog();

        catalog.addProduct("Lego set 8083", "Nice one");
        List<Product> products = catalog.allProducts();

        assertThat(products)
                .hasSize(1);
    }


    @Test
    void itLoadsSingleProductsId() {

        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 80808", "Nice one");


        Product loaded = catalog.getProductBy(id);

        assertThat(id).isEqualTo(loaded.getId());

    }

    @Test
    void itAllowsToChangePrice() {

        ProductCatalog catalog = new ProductCatalog();
        String id = catalog.addProduct("Lego set 80808", "Nice one");


        catalog.changePrice(id, BigDecimal.valueOf(10.10));
        Product loaded = catalog.getProductBy(id);

        assertThat(BigDecimal.valueOf(10.10)).isEqualTo(loaded.getPrice());


    }
}
