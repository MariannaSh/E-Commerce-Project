package mshcherba.ecommerce;

import mshcherba.ecommerce.catalog.ArrayListProductStorage;
import mshcherba.ecommerce.infrastructure.PayUPaymentGw;
import mshcherba.ecommerce.sales.SalesFacade;
import mshcherba.ecommerce.sales.cart.InMemoryCartStorage;
import mshcherba.ecommerce.sales.offer.OfferCalculator;
import mshcherba.ecommerce.sales.reservation.ReservationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import mshcherba.ecommerce.catalog.ProductCatalog;


import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main (String[] args) {
        System.out.println("Here we go");
        SpringApplication.run(App.class,args);
    }

    @Bean
    SalesFacade createSalesFacade() {
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(),
                new PayUPaymentGw(),
                new ReservationRepository()
        );
    }

    @Bean
    ProductCatalog createMyCatalog() {
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        catalog.addProduct("Lego set 8083","nice one ", BigDecimal.valueOf(300));
        catalog.addProduct("Cobi bricks","nice one ", BigDecimal.valueOf(300));

        return catalog;
    }
}