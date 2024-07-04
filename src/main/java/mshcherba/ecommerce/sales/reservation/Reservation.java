package mshcherba.ecommerce.sales.reservation;

import mshcherba.ecommerce.sales.offer.AcceptOfferRequest;
import mshcherba.ecommerce.sales.offer.Offer;
import mshcherba.ecommerce.sales.payment.PaymentDetails;

import java.math.BigDecimal;
import java.time.Instant;

public class Reservation {
    private String reservationId;
    private CustomerDetails customerDetails;
    private Instant paidAt;
    private BigDecimal total;

    public Reservation(String reservationId, CustomerDetails customerDetails, BigDecimal total){

        this.reservationId = reservationId;
        this.customerDetails = customerDetails;
        this.total = total ;
    }
    public static Reservation of(String reservationId, String customerId, AcceptOfferRequest acceptOfferRequest, Offer offer, PaymentDetails paymentDetails) {
        return new Reservation(
            reservationId,
                new CustomerDetails(customerId, acceptOfferRequest.getFname(),acceptOfferRequest.getLname(),acceptOfferRequest.getEmail()),
                offer.getTotal()
        );
    }

    public boolean isPending() {
        return paidAt == null;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getId() {
        return reservationId;
    }
}
