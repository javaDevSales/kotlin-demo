package com.ahpoi.springboot.demo.domain

data class Payment(val id: String,
                   val createdOn: Long,
                   val merchantId: String,
                   val customerId: String,
                   val orderId: String,
                   val amount: Long,
                   val description: String?,
                   val receiptNumber: String,
                   val bankTransactionId: String)

class PaymentException(val errorCode: String,
                       val errorMessage: String) : RuntimeException()