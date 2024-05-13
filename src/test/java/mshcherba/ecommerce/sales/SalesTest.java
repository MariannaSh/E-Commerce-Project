package mshcherba.ecommerce.sales;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class SalesTest {

    @Test
    void itShowOffer(){
        SalesFacade sales = thereIsSalesFacade();
        String customerId= thereIsExampleCustomer("mari");

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(0, offer.getItemsCount());
        assertEquals(BigDecimal.ZERO, offer.getTotal());
    }

    private SalesFacade thereIsSalesFacade() {
        return new SalesFacade();
    }

    private String thereIsExampleCustomer(String id) {
        return id;
    }

    @Test
    void itAllowsProductToCart(){
        String productId = thereIsProduct("Example", BigDecimal.valueOf(10));
        String customerId = thereIsExampleCustomer("Mari");
        SalesFacade sales = thereIsSalesFacade();

        sales.addToCart(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(2,offer.getItemsCount());
        assertEquals(BigDecimal.valueOf(30), offer.getTotal());
    }

    private String thereIsProduct(String example, BigDecimal bigDecimal) {

    }

    @Test
    void itDistinguishCartsByCustomer(){
        String productA = thereIsProduct("Example a", BigDecimal.valueOf(10));
        String productB = thereIsProduct("Example b", BigDecimal.valueOf(10));
        String customerA = thereIsExampleCustomer("Mari");
        String customerB = thereIsExampleCustomer("Masha");
        SalesFacade sales = thereIsSalesFacade();

        sales.addToCart(customerA, productA);
        sales.addToCart(customerB, productB);
        Offer offerA = sales.getCurrentOffer(customerA);
        Offer offerB = sales.getCurrentOffer(customerB);

        assertEquals(1,offerA.getItemsCount());
        assertEquals(BigDecimal.valueOf(10), offerA.getTotal());

        assertEquals(1,offerB.getItemsCount());
        assertEquals(BigDecimal.valueOf(20), offerB.getTotal());
    }

    @Test
    void itAllowsOffer(){
        String productId = thereIsProduct("Example", BigDecimal.valueOf(10));
        String customerId = thereIsExampleCustomer("Mari");
        SalesFacade sales = thereIsSalesFacade();

        sales.addToCart(customerId, productId);
        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(2,offer.getItemsCount());
        assertEquals(BigDecimal.valueOf(30), offer.getTotal());
    }



}
