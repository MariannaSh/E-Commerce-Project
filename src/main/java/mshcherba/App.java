package mshcherba;

import mshcherba.ecommerce.catalog.HasMapProductStorage;
import mshcherba.ecommerce.sales.SalesFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import mshcherba.ecommerce.catalog.ProductCatalog;

@SpringBootApplication

public class App {
    public static void main(String[] args){
        System.out.println("Hello world");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyProductCatalog() {
        var catalog = new ProductCatalog(new HasMapProductStorage());
        catalog.addProduct("Lego set 8083", "Nice done");
        catalog.addProduct("Cobi blocks", "Nice one");

        return catalog;
    }

    @Bean
    SalesFacade createSales() {
        return new SalesFacade();
    }
}
