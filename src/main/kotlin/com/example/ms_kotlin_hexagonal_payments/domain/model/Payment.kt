package com.example.ms_kotlin_hexagonal_payments.domain.model

import com.example.ms_kotlin_hexagonal_payments.domain.exception.BusinessRuleException
import com.example.ms_kotlin_hexagonal_payments.domain.exception.PaymentNotApprovedForRefundException
import com.example.ms_kotlin_hexagonal_payments.domain.exception.RefundAmountGreaterThanPaymentException
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

data class Payment(
    val id: UUID = UUID.randomUUID(),
    val clientId: UUID,
    val amount: Money,
    var status: PaymentStatus = PaymentStatus.CREATED,
    val createdAt: Instant = Instant.now(),
    val authorizationCode: String? = null,
    val declineReason: String? = null
) {

    fun authorize(result: AuthorizationResult): Payment {
        if (status != PaymentStatus.CREATED) {
            throw BusinessRuleException(
                "Payment $id cannot be authorized because status is $status"
            )
        }

        return when (result.status) {
            PaymentStatus.APPROVED -> copy(
                status = PaymentStatus.APPROVED,
                authorizationCode = result.authorizationCode,
                declineReason = null
            )

            PaymentStatus.DECLINED -> copy(
                status = PaymentStatus.DECLINED,
                declineReason = result.declineReason ?: "DECLINED",
                authorizationCode = null
            )

            else -> throw BusinessRuleException(
                "Invalid authorization result status: ${result.status}"
            )
        }
    }

    fun validateRefundEligibility(refundAmount: BigDecimal) {

        // REGRA 1
        if (status != PaymentStatus.APPROVED) {
            throw PaymentNotApprovedForRefundException()
        }

        // REGRA 2
        if (refundAmount <= BigDecimal.ZERO) {
            throw BusinessRuleException(
                "Refund amount must be greater than zero"
            )
        }

        // REGRA 3
        if (refundAmount > amount.amount) {
            throw RefundAmountGreaterThanPaymentException()
        }
    }

    fun markAsRefunded(): Payment {
        if (status != PaymentStatus.APPROVED) {
            throw BusinessRuleException(
                "Payment $id cannot be refunded because status is $status"
            )
        }

        return copy(status = PaymentStatus.REFUNDED)
    }
}