package mshcherba.ecommerce.sales.offer;

import mshcherba.ecommerce.catalog.ProductCatalog;
import mshcherba.ecommerce.sales.cart.Cart;
import mshcherba.ecommerce.sales.cart.CartLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


@SpringBootTest
public class OfferCalculatorTest {

    @Autowired
    private OfferCalculator offerCalculator;

    @Autowired
    private ProductCatalog catalog;

    @Test
    void itCalculatesOffer() {

        Cart cart = Cart.empty();


        catalog.addProduct("Lego set 8083", "Nice one", BigDecimal.valueOf(10), "https://example.com/product");
        catalog.addProduct("Cobi Blocks", "Nice one", BigDecimal.valueOf(5), "https://example.com/product");


        String productId1 = catalog.allProducts().get(0).getId();
        String productId2 = catalog.allProducts().get(1).getId();


        for (int i = 0; i < 10; i++) {
            cart.addProduct(productId1);
        }

        for (int i = 0; i < 5; i++) {
            cart.addProduct(productId2);
        }


        List<CartLine> lines = cart.getItems();
        Offer result = offerCalculator.calculate(lines);


        assertThat(result.getItemsCount()).isEqualTo(12);
        assertThat(result.getTotal()).isGreaterThan(BigDecimal.ZERO);
        assertThat(result.getTotal()).isGreaterThanOrEqualTo(BigDecimal.ZERO);


    }
}
