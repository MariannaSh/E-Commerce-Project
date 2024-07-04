package mshcherba.ecommerce.sales.reservation;

import mshcherba.ecommerce.sales.payment.RegisterPaymentRequest;
import mshcherba.ecommerce.sales.payment.PaymentDetails;
import mshcherba.ecommerce.sales.payment.PaymentGateway;

public class SpyPaymentGateway implements PaymentGateway {
    Integer requestCount = 0;
    public RegisterPaymentRequest lastRequest;

    public Integer getRequestsCount() {
        return requestCount;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        this.requestCount++;
        lastRequest = registerPaymentRequest;
        return new PaymentDetails("http://spy-gateway");
    }
}
