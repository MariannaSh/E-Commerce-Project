package ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication


public class App {
    public static void main(String[] args){
        System.out.println("Hello world");
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyProductCatalog() {
        ProductCatalog productCatalog=new ProductCatalog();
        productCatalog.addProduct("Lego set 7087","Nice one");
        productCatalog.addProduct("Lego 23","Nice one");

        return productCatalog;
    }
}
