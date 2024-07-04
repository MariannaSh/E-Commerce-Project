package mshcherba.ecommerce.sales;

import mshcherba.ecommerce.sales.cart.Cart;
import mshcherba.ecommerce.sales.cart.InMemoryCartStorage;
import mshcherba.ecommerce.sales.offer.AcceptOfferRequest;
import mshcherba.ecommerce.sales.offer.Offer;
import mshcherba.ecommerce.sales.offer.OfferCalculator;
import mshcherba.ecommerce.sales.payment.PaymentDetails;
import mshcherba.ecommerce.sales.payment.PaymentGateway;
import mshcherba.ecommerce.sales.payment.RegisterPaymentRequest;
import mshcherba.ecommerce.sales.reservation.Reservation;
import mshcherba.ecommerce.sales.reservation.ReservationDetails;
import mshcherba.ecommerce.sales.reservation.ReservationRepository;

import java.util.UUID;

public class SalesFacade {
    private OfferCalculator offerCalculator;
    private InMemoryCartStorage cartStorage;
    private PaymentGateway paymentGateway;
    private ReservationRepository reservationRepository;

    public SalesFacade(InMemoryCartStorage cartStorage, OfferCalculator offerCalculator, PaymentGateway paymentGateway, ReservationRepository reservationRepository){
        this.cartStorage = cartStorage;
        this.offerCalculator = offerCalculator;
        this.paymentGateway = paymentGateway;
        this.reservationRepository = reservationRepository;
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);

        cartStorage.save(customerId, cart);
    }

    private Cart loadCartForCustomer(String customerId) {
        return cartStorage.findByCustomerId(customerId)
                .orElse(Cart.empty());
    }

    public Offer getCurrentOffer(String customerId) {
        Cart cart = cartStorage.findByCustomerId(customerId).orElse(Cart.empty());

        Offer offer = offerCalculator.calculate(cart.getItems());

        return offer;
    }

    public ReservationDetails acceptOffer(String customerId, AcceptOfferRequest acceptOfferRequest){
        String reservationId = UUID.randomUUID().toString();
        Offer offer = this.getCurrentOffer(customerId);



        PaymentDetails paymentDetails = paymentGateway.registerPayment(
                RegisterPaymentRequest.of(reservationId, acceptOfferRequest, offer.getTotal())
        );
        Reservation reservation = Reservation.of(
                reservationId,
                customerId,
                acceptOfferRequest,
                offer,
                paymentDetails
        );

        reservationRepository.add(reservation);
        return new ReservationDetails(reservationId, paymentDetails.getPaymentUrl(), offer.getTotal());
    }


}