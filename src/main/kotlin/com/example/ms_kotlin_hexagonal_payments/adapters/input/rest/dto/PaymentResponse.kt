package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto

import com.example.ms_kotlin_hexagonal_payments.domain.model.PaymentStatus
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

data class PaymentResponse(
    val id: UUID,
    val clientId: UUID,
    val amount: BigDecimal,
    val currency: String,
    val status: PaymentStatus,
    val createdAt: Instant
)
