package mshcherba.ecommerce;

import mshcherba.ecommerce.catalog.ArrayListProductStorage;
import mshcherba.ecommerce.payu.PayU;
import mshcherba.ecommerce.payu.PayUCredentials;
import mshcherba.ecommerce.playground.SqlProductStorage;
import mshcherba.ecommerce.sales.SalesFacade;
import mshcherba.ecommerce.sales.cart.InMemoryCartStorage;
import mshcherba.ecommerce.sales.offer.OfferCalculator;
import mshcherba.ecommerce.sales.payment.PaymentGateway;
import mshcherba.ecommerce.sales.reservation.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import mshcherba.ecommerce.catalog.ProductCatalog;
import org.springframework.web.client.RestTemplate;


import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main (String[] args) {
        System.out.println("Here we go");
        SpringApplication.run(App.class,args);
    }

    @Bean
    public SqlProductStorage sqlProductStorage() {
        return new SqlProductStorage();
    }

    @Bean
    PaymentGateway createPaymentGateway(){
        return new PayU(
                new RestTemplate(),
                PayUCredentials.sandbox(
                        "300746",
                        "2ee86a66e5d97e3fadc400c9f19b065d"
                )
        );

    }

    @Bean
    SalesFacade createSalesFacade(@Autowired ProductCatalog productCatalog) {
        return new SalesFacade(
                new InMemoryCartStorage(),
                new OfferCalculator(productCatalog),
                createPaymentGateway(),
                new ReservationRepository()
        );
    }

    @Bean
    ProductCatalog createMyCatalog() {
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        catalog.addProduct("Nike V2K Run", "nice one", BigDecimal.valueOf(539.00), "https://static.cdek.shopping/images/shopping/BiX8ncCkxgtGxrWN.jpg");
        catalog.addProduct("Nike Dunk Low", "nice one", BigDecimal.valueOf(469.00), "https://images.unsplash.com/photo-1623684225794-a8f1f5037f5c?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        catalog.addProduct("Adidas Superstar", "fast and furious", BigDecimal.valueOf(499.00), "https://as2.ftcdn.net/v2/jpg/06/15/34/67/1000_F_615346768_cIIZ63pr4Vo40o7CAbxgbUVvBmstkSB0.jpg");
        catalog.addProduct("Grey Adidas Sneakers", "challenging puzzle", BigDecimal.valueOf(468.00), "https://as1.ftcdn.net/v2/jpg/08/70/45/78/1000_F_870457807_9EFNBIpGhTB3o0G32C78lfUksB78RkIe.jpg");
        catalog.addProduct("ANike Kawa Slide", "collectible item", BigDecimal.valueOf(273.00), "https://as1.ftcdn.net/v2/jpg/03/54/31/02/1000_F_354310247_s39R7NJ4c6bq3PgdPndrYFXfNfwQm2eV.jpg");
        catalog.addProduct("Adidas Samba", "collectible item", BigDecimal.valueOf(195.00), "https://as2.ftcdn.net/v2/jpg/03/54/30/75/1000_F_354307540_B0L929CMYQ9a6UH5rjtwzLPT8tA4L5AN.jpg");
        catalog.addProduct("Nike Air Jordan 1", "fast and furious", BigDecimal.valueOf(620.00), "https://as1.ftcdn.net/v2/jpg/03/54/89/62/1000_F_354896205_pIvNvRUkbDG9MY1cCUPz2gFnaqtX4I2k.jpg");
        catalog.addProduct("Nike Air Max 95", "challenging puzzle", BigDecimal.valueOf(391.00), "https://as1.ftcdn.net/v2/jpg/03/54/71/48/1000_F_354714805_E2yHvW4IYKgZWv0jRXXKL6mBi997ZvDe.jpg");
        catalog.addProduct("Nike Air", "collectible item", BigDecimal.valueOf(254.00), "https://as2.ftcdn.net/v2/jpg/03/54/87/23/1000_F_354872397_sxGaBqJKXjRTz442M5coivJb3TvNDSiw.jpg");
        catalog.addProduct("Converse Chuck Taylor", "fast and furious", BigDecimal.valueOf(290.00), "https://as2.ftcdn.net/v2/jpg/03/54/88/05/1000_F_354880505_N0wcdTfE3ns5V90RNciC7a18wVvUf1gt.jpg");
        catalog.addProduct("Nike Dunk Low Pro", "challenging puzzle", BigDecimal.valueOf(339.00), "https://as2.ftcdn.net/v2/jpg/03/54/88/19/1000_F_354881964_qAxRglregYlLbQYu46WTBiT2ofmBAvkq.jpg");
        catalog.addProduct("Asics Gel", "perfect for kids", BigDecimal.valueOf(245.00), "https://as1.ftcdn.net/v2/jpg/03/54/72/72/1000_F_354727284_tlRet2jAHJoVxBt9i9TOZausbmagd2cN.jpg");

        return catalog;
    }
}