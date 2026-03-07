package com.example.ms_kotlin_hexagonal_payments.domain.model

import com.example.ms_kotlin_hexagonal_payments.domain.exception.BusinessException
import com.example.ms_kotlin_hexagonal_payments.domain.exception.BusinessRuleException
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
            throw BusinessRuleException("Payment $id cannot be authorized because status is $status")
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
            else -> throw BusinessRuleException("Invalid authorization result status: ${result.status}")
        }
    }

    fun validateRefundEligibility(refundAmount: BigDecimal) {
        if (status != PaymentStatus.APPROVED) {
            throw BusinessException("Apenas pagamentos com status APPROVED podem ser estornados")
        }

        if (refundAmount <= BigDecimal.ZERO) {
            throw BusinessException("O valor do estorno deve ser maior que zero")
        }

        if (refundAmount > amount.value) {
            throw BusinessException("O valor do estorno não pode ser maior que o valor do pagamento")
        }
    }

    fun markAsRefunded() {
        this.status = PaymentStatus.REFUNDED
    }
}
