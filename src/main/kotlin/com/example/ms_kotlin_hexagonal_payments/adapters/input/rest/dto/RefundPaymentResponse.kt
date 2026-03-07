package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto

import java.math.BigDecimal
import java.util.UUID

data class RefundPaymentResponse(
    val refundId: UUID,
    val paymentId: UUID,
    val status: String,
    val refundedAmount: BigDecimal,
    val reason: String
)
