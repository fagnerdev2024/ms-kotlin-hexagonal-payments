package com.example.ms_kotlin_hexagonal_payments.application.port.output



import java.math.BigDecimal
import java.util.UUID

interface PaymentRefundPort {
    fun refund(command: RefundGatewayCommand): RefundGatewayResult
}

data class RefundGatewayCommand(
    val paymentId: UUID,
    val amount: BigDecimal,
    val reason: String
)

data class RefundGatewayResult(
    val success: Boolean,
    val externalId: String?,
    val message: String?
)