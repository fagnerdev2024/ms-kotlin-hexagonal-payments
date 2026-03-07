package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity

import com.example.ms_kotlin_hexagonal_payments.domain.model.RefundStatus


import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "refunds")
class RefundEntity(

    @Id
    val id: UUID,

    @Column(nullable = false)
    val paymentId: UUID,

    @Column(nullable = false, precision = 19, scale = 2)
    val amount: BigDecimal,

    @Column(nullable = false)
    val reason: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: RefundStatus,

    @Column(nullable = false)
    val createdAt: LocalDateTime
)