package com.ahpoi.springboot.demo

import com.ahpoi.springboot.demo.domain.Payment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

data class PaymentRequest(val merchantId: String,
                          val customerId: String,
                          val orderId: String,
                          val description: String?,
                          val amount: Long)

/**
 * When the AppUser get compiles to java we get something like:
 * public final class PaymentResponse {
 *      private final String createdOn;
 *      private final String id:
 *      private final String orderId;
 *      ..etc the fields
 *
 *      //Constructor
 *      //Getters
 *      //copy(),hashCode(), equals()
 *
 *      //Companion Object:
 *        public static final class Companion {
 *
 *          public final PaymentResponse toPaymentResponse(Payment payment) {
 *              return .....
 *          }
 *        }
 * }
 */
data class PaymentResponse(val createdOn: Long,
                           val id: String,
                           val orderId: String,
                           val amount: Long,
                           val description: String?,
                           val receiptNumber: String) {

    companion object {
        fun toPaymentResponse(payment: Payment) = PaymentResponse(
                id = payment.id,
                createdOn = payment.createdOn,
                orderId = payment.orderId,
                amount = payment.amount,
                description = payment.description,
                receiptNumber = payment.receiptNumber)
    }
}


@RestController
open class PaymentController(val paymentService: PaymentService) {

    @PostMapping(value = ["v1/payments"])
    fun makePayment(@RequestBody req: PaymentRequest): ResponseEntity<PaymentResponse> {
        val payment = paymentService.makePayment(req.merchantId, req.customerId, req.orderId, req.amount)
        return status(HttpStatus.OK).body(PaymentResponse.toPaymentResponse(payment))
    }

    /**
     * Null check with ? and using elvis operator
     */
    @GetMapping(value = ["v1/payments/{id}"])
    fun getPayment(@PathVariable("id") id: String): ResponseEntity<PaymentResponse> {
        return paymentService.getPayment(id)?.let { payment -> status(HttpStatus.OK).body(PaymentResponse.toPaymentResponse(payment)) } ?: notFound().build()
    }

    @GetMapping(value = ["v1/payments"])
    fun getPayments(): ResponseEntity<List<PaymentResponse>> {
        val paymentListResp: List<PaymentResponse> = paymentService.getPayments().map { payment -> PaymentResponse.toPaymentResponse(payment) }
        return status(HttpStatus.OK).body(paymentListResp)
    }

}