package mshcherba.ecommerce.sales;

import mshcherba.ecommerce.catalog.ProductCatalog;
import mshcherba.ecommerce.sales.offer.AcceptOfferRequest;
import mshcherba.ecommerce.sales.offer.Offer;
import mshcherba.ecommerce.sales.reservation.ReservationDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHTTPTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Test
    void itAllowsToAcceptOfferHappyPath() {
        var productId = thereIsProduct("product-x");
        var addProductToCartUrl = String.format(
                "http://localhost:%s/api/add-product/%s",
                port,
                productId);

        ResponseEntity<Void> addProductResponse = http.postForEntity(addProductToCartUrl, null, Void.class);

        var getCurrentOfferUrl = String.format("http://localhost:%s/api/current-offer", port);
        ResponseEntity<Offer> offerResponse = http.getForEntity(getCurrentOfferUrl, Offer.class);

        var acceptOfferUrl = String.format("http://localhost:%s/api/accept-offer", port);

        var acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFname("Zuza")
                .setLname("Zimmer")
                .setEmail("zuza@gmail.pl");

        ResponseEntity<ReservationDetails> acceptOfferResponse = http.postForEntity(
                acceptOfferUrl,acceptOfferRequest,ReservationDetails.class
        );




        assertEquals(HttpStatus.OK, addProductResponse.getStatusCode());
        assertEquals(HttpStatus.OK, offerResponse.getStatusCode());
        assertEquals(HttpStatus.OK, acceptOfferResponse.getStatusCode());
        assertNotNull(acceptOfferResponse.getBody().getPaymentUrl());
        assertNotNull(acceptOfferResponse.getBody().getReservationId());

    }

    private String thereIsProduct(String id) {
        return id;
    }
}