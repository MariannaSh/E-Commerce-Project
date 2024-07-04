package mshcherba.ecommerce.sales.payment;

import mshcherba.ecommerce.payu.OrderCreateRequest;

public interface PaymentGateway {
    PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest);

}
