package com.ahpoi.springboot.demo;

import com.ahpoi.springboot.demo.domain.Payment;
import com.ahpoi.springboot.demo.utils.UtilsKt;

import java.util.Date;
import java.util.UUID;

public class CallingKotlinFromJava {

    /**
     * Calling a companion object method
     */
    public void callingCompanionObject() {
        Payment payment = new Payment(UUID.randomUUID().toString(),
                new Date().getTime(),
                "merchantId132",
                "customerId123",
                "orderId123",
                1000,
                null,
                UUID.randomUUID().toString(),
                "bankTxnId123");

        PaymentResponse.Companion.toPaymentResponse(payment);
    }

    /**
     * File name is `Utils`, but when it gets compiled gets a class name of `UtilsKt`
     */
    public void callingKotlinFileFunction() {
        UtilsKt.generateReceipt();
    }

}
