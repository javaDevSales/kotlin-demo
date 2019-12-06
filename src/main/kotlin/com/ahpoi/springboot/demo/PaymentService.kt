package com.ahpoi.springboot.demo

import com.ahpoi.springboot.demo.domain.Payment
import com.ahpoi.springboot.demo.domain.PaymentException
import com.ahpoi.springboot.demo.payhiveclient.PayhivePaymentClient
import com.ahpoi.springboot.demo.payhiveclient.PayhivePaymentClientConfig
import com.ahpoi.springboot.demo.payhiveclient.PayhivePaymentRequest
import com.ahpoi.springboot.demo.payhiveclient.PayhivePaymentResponse
import com.ahpoi.springboot.demo.paypalclient.PayPalClient
import com.ahpoi.springboot.demo.paypalclient.PayPalClientConfigV1
import com.ahpoi.springboot.demo.paypalclient.PayPalClientConfigV2
import org.springframework.stereotype.Service
import java.util.*

/**
 * Access Java code using apply(Simulate a Fluent Setter Pattern) as you get a reference to 'this':
 */
private val paypalClientConfigV1: PayPalClientConfigV1 = PayPalClientConfigV1().apply {
    rootUrl = "https://paypal-api-v1.com"
    username = "username"
    password = "password"
    readTimeout = 60L
    connectTimeout = 60L
}

/**
 * Access Java code:  Builder Pattern:
 */
private val paypalClientConfigV2: PayPalClientConfigV2 = PayPalClientConfigV2.PayPalPaymentClientConfigV2Builder.newBuilder()
        .withRootUrl("https://paypal-api-v1.com")
        .withUsername("username")
        .withPassword("password")
        .withReadTimeout(60L)
        .withConnectTimeout(60L)
        .build()

private val paypalClientV1 = PayPalClient(paypalClientConfigV1)

/**
 * Kotlin Builder Like:
 * named params
 * default params
 */
private val payhiveClientConfig = PayhivePaymentClientConfig(
        rootUrl = "https://payhive-api.com",
        username = "username",
        password = "password",
        readTimeout = 60L,
        connectTimeout = 60L)

private val payhivePaymentClient = PayhivePaymentClient(payhiveClientConfig)

@Service
open class PaymentService {

    /**
     * Making used of the sealed class PayhivePaymentResponse
     * 'when' must be exhaustive when using a sealed class
     * smart casting
     * compile check when adding new type
     */
    fun makePayment(merchantId: String, customerId: String, orderId: String, amount: Long): Payment {
        val req = PayhivePaymentRequest(
                merchantCode = merchantId,
                orderCode = orderId,
                amount = amount,
                currency = "AUD")
        val res = payhivePaymentClient.makePayment(req)
        return when (res) {
            is PayhivePaymentResponse.Success -> Payment(UUID.randomUUID().toString(), Date().time, merchantId, customerId, orderId, res.amount, null, UUID.randomUUID().toString(), res.bankTxnId)
            is PayhivePaymentResponse.Failed -> throw PaymentException(res.errorCode, res.errorMessage)
        }
    }

    /**
     * Can be Null value with ?
     * Passing null for description as field is marked as nullable using ?
     */
    fun getPayment(id: String): Payment? {
        return when (id) {
            "1" -> Payment(
                    id = UUID.randomUUID().toString(),
                    createdOn = Date().time,
                    merchantId = "merchantId132",
                    customerId = "customerId123",
                    orderId = "orderId123",
                    amount = 1000,
                    description = null,
                    receiptNumber = UUID.randomUUID().toString(),
                    bankTransactionId = "bankTxnId123")
            else -> null
        }
    }

    /**
     * Read only collections
     */
    fun getPayments(): List<Payment> {
        val payment1 = Payment(id = UUID.randomUUID().toString(),
                createdOn = Date().time,
                merchantId = "merchantId132",
                customerId = "customerId123",
                orderId = "orderId123",
                amount = 1000,
                description = null,
                receiptNumber = UUID.randomUUID().toString(),
                bankTransactionId = "bankTxnId123")
        val payment2 = payment1.copy(id = UUID.randomUUID().toString(), description = "New Description")

        val readOnlyPayments = listOf(payment1, payment2)
        //readOnlyPayments.add(payment1) - No add method for the listOf()

        val mutablePayments = mutableListOf(payment1, payment2)
        mutablePayments.add(payment1)

        return readOnlyPayments
    }


}