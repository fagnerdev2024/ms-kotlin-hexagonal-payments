package com.example.ms_kotlin_hexagonal_payments.application.port.input


import java.math.BigDecimal
import java.util.UUID

interface RefundPaymentUseCase {
    fun execute(command: RefundPaymentCommand): RefundPaymentResult
}

data class RefundPaymentCommand(
    val paymentId: UUID,
    val amount: BigDecimal,
    val reason: String
)

data class RefundPaymentResult(
    val refundId: UUID,
    val paymentId: UUID,
    val status: String,
    val refundedAmount: BigDecimal,
    val reason: String
)