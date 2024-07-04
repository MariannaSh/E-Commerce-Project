package mshcherba.ecommerce.infrastructure;

import mshcherba.ecommerce.payu.OrderCreateRequest;
import mshcherba.ecommerce.payu.OrderCreateResponse;
import mshcherba.ecommerce.payu.PayU;
import mshcherba.ecommerce.sales.payment.PaymentDetails;
import mshcherba.ecommerce.sales.payment.PaymentGateway;
import mshcherba.ecommerce.sales.payment.RegisterPaymentRequest;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

//public class PayUPaymentGw implements PaymentGateway {
//
//}