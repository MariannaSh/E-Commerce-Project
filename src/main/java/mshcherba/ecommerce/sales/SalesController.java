package mshcherba.ecommerce.sales;


import mshcherba.ecommerce.sales.offer.AcceptOfferRequest;
import mshcherba.ecommerce.sales.offer.Offer;
import mshcherba.ecommerce.sales.reservation.ReservationDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class SalesController {

    SalesFacade sales;

    public SalesController(SalesFacade sales) {
        this.sales = sales;
    }

    @PostMapping("/api/add-product/{productId}")
    void addProduct (@PathVariable(name = "productId") String productId){
        String customerId = getCurrentCustomer();
        sales.addToCart(customerId, productId);
    }

    @PostMapping("/api/accept-offer")
    ReservationDetails acceptOffer(@RequestBody AcceptOfferRequest acceptOfferRequest){
        String customerId = getCurrentCustomer();
        ReservationDetails details = sales.acceptOffer(customerId, acceptOfferRequest);
        return details;
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer() {
        String customerId = getCurrentCustomer();
        return sales.getCurrentOffer(customerId);
    }

    private String getCurrentCustomer() {
        return "guest";
    }
}