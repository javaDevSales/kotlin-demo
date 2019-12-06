package com.ahpoi.springboot.demo.paypalclient;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class PayPalClientV2 {

    private final PayPalClientConfigV2 payPalClientConfig;

    public PayPalClientV2(PayPalClientConfigV2 payPalClientConfig) {
        this.payPalClientConfig = payPalClientConfig;
    }

    public void initiateTxn() {
        throw new NotImplementedException();
    }
}
