package mshcherba.ecommerce.sales.reservation;

import mshcherba.ecommerce.sales.payment.RegisterPaymentRequest;
import mshcherba.ecommerce.sales.payment.PaymentDetails;
import mshcherba.ecommerce.sales.payment.PaymentGateway;
import java.util.UUID;

public class SpyPaymentGateway implements PaymentGateway {
    Integer requestCount = 0;
    public Integer getRequestsCount() {
        return requestCount;
    }

    @Override
    public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
        this.requestCount++;
        return new PaymentDetails("http://spy-gateway", UUID.randomUUID().toString());
    }
}
