package mshcherba.ecommerce.sales.offer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Collections;


@SpringBootTest
public class OfferCalculatorTest {


    @Test
    void zeroOfferForEmptyItems(){
        OfferCalculator calculator = new OfferCalculator();

        Offer offer = calculator.calculate(Collections.emptyList());

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.ZERO);
    }
}