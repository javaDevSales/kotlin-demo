package com.ahpoi.springboot.demo.paypalclient;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PayPalClient {

    private final PayPalClientConfigV1 payPalClientConfig;

    public PayPalClient(PayPalClientConfigV1 payPalClientConfig) {
        this.payPalClientConfig = payPalClientConfig;
    }

    public void initiateTxn() {
        throw new NotImplementedException();
    }
}
