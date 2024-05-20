package mshcherba.ecommerce.sales;

import mshcherba.ecommerce.sales.cart.Cart;
import mshcherba.ecommerce.sales.cart.InMemoryCartStorage;
import mshcherba.ecommerce.sales.offer.Offer;
import mshcherba.ecommerce.sales.offer.OfferCalculator;


public class SalesFacade {

    private InMemoryCartStorage cartStorage;
    private final OfferCalculator calculator;
    private OfferCalculator offerCalculator;



    public SalesFacade(InMemoryCartStorage cartStorage, OfferCalculator calculator) {
        this.cartStorage = cartStorage;
        this.calculator = calculator;

    }
    public Offer getCurrentOffer(String customerId) {
        Cart cart = loadCartForCustomer(customerId);


        return offerCalculator.calculate(cart.getLines());
    }

    public ReservationDetails acceptOffer(String customerId) {
        return new ReservationDetails();
    }

    public void addToCart(String customerId, String productId) {
        Cart cart = loadCartForCustomer(customerId);

        cart.addProduct(productId);
    }

    private Cart loadCartForCustomer(String customerId) {

        return cartStorage.findByCustomerId(customerId)
                .orElse(Cart.empty());
    }
}
