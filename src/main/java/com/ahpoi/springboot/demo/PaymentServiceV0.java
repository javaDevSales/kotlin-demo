package com.ahpoi.springboot.demo;

import com.ahpoi.springboot.demo.payhiveclient.PayhivePaymentClient;
import com.ahpoi.springboot.demo.payhiveclient.PayhivePaymentClientConfig;
import com.ahpoi.springboot.demo.payhiveclient.PayhivePaymentRequest;
import com.ahpoi.springboot.demo.payhiveclient.PayhivePaymentResponse;

/**
 * Example calling Kotlin Code from Java
 */
public class PaymentServiceV0 {

    public PayhivePaymentClient payhivePaymentClient;

    public PaymentServiceV0() {
        PayhivePaymentClientConfig config = new PayhivePaymentClientConfig("https://payhive-api.com", "username", "password", 60L, 60L);
        this.payhivePaymentClient = new PayhivePaymentClient(config);
    }

    public String makePayment() {
        final PayhivePaymentRequest req = new PayhivePaymentRequest("merchant123", "order12", 1000L, "AUD");
        final PayhivePaymentResponse res = this.payhivePaymentClient.makePayment(req);
        if (res instanceof PayhivePaymentResponse.Success) {
            return "Success";
        } else if (res instanceof PayhivePaymentResponse.Failed) {
            return "Failure";
        }
        return "Failure";
    }

}
