package com.example.ms_kotlin_hexagonal_payments.domain.model

import java.time.Instant
import java.util.UUID

data class Payment(
    val id: UUID = UUID.randomUUID(),
    val clientId: UUID,
    val amount: Money,
    val status: PaymentStatus = PaymentStatus.CREATED,
    val createdAt: Instant = Instant.now()
) {
    init {
        requireNotNull(clientId) { "clientId must not be null" }
    }
}
