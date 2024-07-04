package mshcherba.ecommerce.sales.reservation;


import java.math.BigDecimal;
import java.util.Optional;

import mshcherba.ecommerce.catalog.ProductCatalog;
import mshcherba.ecommerce.sales.cart.CartLine;
import mshcherba.ecommerce.sales.cart.InMemoryCartStorage;
import mshcherba.ecommerce.sales.offer.AcceptOfferRequest;
import mshcherba.ecommerce.sales.SalesFacade;
import mshcherba.ecommerce.sales.offer.OfferCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class OfferAcceptanceTest {
    private SpyPaymentGateway spyPaymentGateway;
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp(){
        spyPaymentGateway = new SpyPaymentGateway();
        reservationRepository = new ReservationRepository();
    }

    @Test
    void itAllowsToAcceptAnOffer() {
        SalesFacade sales = thereIsSales();
        String customerId = thereIsCustomer("Mari");
        String productId = thereIsProduct("X", BigDecimal.valueOf(300));

        sales.addToCart(customerId, productId);
        sales.addToCart(customerId, productId);

        var acceptOfferRequest = new AcceptOfferRequest();
        acceptOfferRequest
                .setFname("Mari")
                .setLname("Shcherba")
                .setEmail("mari@gmail.pl");

        ReservationDetails reservationDetails = sales.acceptOffer(customerId, acceptOfferRequest);

        assertThat(reservationDetails.getPaymentUrl()).isNotBlank();
        assertThat(reservationDetails.getReservationId()).isNotBlank();

        assertPaymentHasBeenRegistered();
        asserThereIsReservationWithId(reservationDetails.getReservationId());
        asserReservationIsPending(reservationDetails.getReservationId());
        asserReservationIsDoneForCustomer(reservationDetails.getReservationId(), "Mari", "Shcherba", "mari@gmail.pl");
        asserReservationTotalMatchOffer(reservationDetails.getReservationId(), BigDecimal.valueOf(300));

    }

    private void asserReservationTotalMatchOffer(String reservationId, BigDecimal expectedTotal) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        assertThat(loaded.getTotal()).isEqualTo(expectedTotal);
    }

    private void asserReservationIsDoneForCustomer(String reservationId, String fname, String lname, String email) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        CustomerDetails clientData = loaded.getCustomerDetails();

        assertThat(clientData.getFirstName()).isEqualTo(fname);
        assertThat(clientData.getLastName()).isEqualTo(lname);
        assertThat(clientData.getEmail()).isEqualTo(email);
    }

    private void asserReservationIsPending(String reservationId) {
        Reservation loaded = reservationRepository.load(reservationId)
                .get();

        assertThat(loaded.isPending()).isTrue();
    }

    private void asserThereIsReservationWithId(String reservationId) {
        Optional<Reservation> loaded = reservationRepository.load(reservationId);

        assertThat(loaded).isPresent();
    }

    private void assertPaymentHasBeenRegistered() {

        assertThat(spyPaymentGateway.getRequestsCount()).isEqualTo(1);
    }

    private String thereIsProduct(String productId, BigDecimal bigDecimal) {
        return productId;
    }

    private String thereIsCustomer(String id) {
        return id;
    }

    private SalesFacade thereIsSales() {
        return new SalesFacade(
               new InMemoryCartStorage(),
                new OfferCalculator(),
                spyPaymentGateway,
                reservationRepository
        );
    }
}