package com.example.ms_kotlin_hexagonal_payments.domain.model

import com.example.ms_kotlin_hexagonal_payments.domain.exception.BusinessRuleException
import java.time.Instant
import java.util.UUID

data class Payment(
    val id: UUID = UUID.randomUUID(),
    val clientId: UUID,
    val amount: Money,
    val status: PaymentStatus = PaymentStatus.CREATED,
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
}
