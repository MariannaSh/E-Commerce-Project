package mshcherba.ecommerce.infrastructure;

import mshcherba.ecommerce.sales.payment.PaymentDetails;
import mshcherba.ecommerce.sales.payment.PaymentGateway;
import mshcherba.ecommerce.sales.payment.RegisterPaymentRequest;
import org.springframework.web.client.RestTemplate;

public class PayUPaymentGw implements PaymentGateway {
    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        return null;
    }
}