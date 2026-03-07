package com.example.ms_kotlin_hexagonal_payments.domain.model



import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class Refund(
    val id: UUID? = null,
    val paymentId: UUID,
    val amount: BigDecimal,
    val reason: String,
    val status: RefundStatus,
    val createdAt: LocalDateTime = LocalDateTime.now()
)