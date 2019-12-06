package com.ahpoi.springboot.demo.payhiveclient

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

/**
 * Default params
 */
data class PayhivePaymentClientConfig(val rootUrl: String,
                                      val username: String,
                                      val password: String,
                                      val connectTimeout: Long = 60L,
                                      val readTimeout: Long = 60L)

class PayhivePaymentClient(private val config: PayhivePaymentClientConfig) {

    fun makePayment(req: PayhivePaymentRequest): PayhivePaymentResponse = when (req.amount) {
        1000L -> PayhivePaymentResponse.Success(Date().time, Date().time, req.amount, "BankId-123")
        2000L -> PayhivePaymentResponse.Failed(errorCode = "1001", errorMessage = "Insufficient Funds")
        else -> throw IllegalArgumentException("Internal Error")
    }

}


/**
 * Dtos for the PayhivePayment Client:
 * We usually separates it in a Kotlin File, called PayhiveDtos
 */
data class PayhivePaymentRequest @JsonCreator constructor(@JsonProperty("merchant_code") val merchantCode: String,
                                                          @JsonProperty("order_code") val orderCode: String,
                                                          @JsonProperty("amount") val amount: Long,
                                                          @JsonProperty("currency") val currency: String)

sealed class PayhivePaymentResponse {

    data class Success(val created: Long,
                       val updated: Long,
                       val amount: Long,
                       val bankTxnId: String) : PayhivePaymentResponse()

    data class Failed(val errorCode: String,
                      val errorMessage: String) : PayhivePaymentResponse()

}
