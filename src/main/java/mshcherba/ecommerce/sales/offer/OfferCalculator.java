package mshcherba.ecommerce.sales.offer;
import mshcherba.ecommerce.sales.cart.CartLine;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
public class OfferCalculator {

    public Offer calculate(List<CartLine> lines) {
        BigDecimal basePrice = BigDecimal.valueOf(300);
        BigDecimal totalPrice = basePrice.multiply(new BigDecimal(lines.size()));
        BigDecimal threshold = BigDecimal.valueOf(500);
        BigDecimal discount50 = BigDecimal.valueOf(50);

        int productCount = lines.size();
        BigDecimal discount = BigDecimal.ZERO;


        if (productCount%3==0 && productCount != 0){
            productCount++;
        }
        if(totalPrice.compareTo(threshold) >= 0){
            totalPrice = totalPrice.subtract(discount50);
        }

        BigDecimal finalPrice = totalPrice.subtract(discount);

        return new Offer(finalPrice, productCount);

    }
}