package mshcherba.ecommerce.sales.offer;
import mshcherba.ecommerce.catalog.Product;
import mshcherba.ecommerce.catalog.ProductCatalog;
import mshcherba.ecommerce.sales.cart.CartLine;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
public class OfferCalculator {

    private final ProductCatalog productCatalog;

    public OfferCalculator(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public Offer calculate(List<CartLine> lines) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        int productCount = 0;

        for (CartLine line : lines) {
            Product product = productCatalog.getProductBy(line.getProductId());
            BigDecimal linePrice = product.getPrice().multiply(BigDecimal.valueOf(line.getQty()));
            totalPrice = totalPrice.add(linePrice);
            productCount += line.getQty();
        }

        BigDecimal threshold = BigDecimal.valueOf(600);
        BigDecimal discountRate = BigDecimal.valueOf(0.30);
        BigDecimal discount = BigDecimal.ZERO;

        // Проверяем, превышает ли общая стоимость порог для скидки
        if (totalPrice.compareTo(threshold) > 0) {
            // Вычисляем количество продуктов, на которые предоставляется скидка
            int discountedItemCount = productCount / 3;
            BigDecimal discountedAmount = productCatalog.getProductBy(lines.get(0).getProductId()).getPrice()
                    .multiply(BigDecimal.valueOf(discountedItemCount)).multiply(discountRate);
            discount = discountedAmount;
        }

        BigDecimal finalPrice = totalPrice.subtract(discount);

        return new Offer(finalPrice, productCount);
    }
}
