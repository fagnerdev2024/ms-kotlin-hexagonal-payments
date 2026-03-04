package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity


import com.example.ms_kotlin_hexagonal_payments.domain.model.PaymentStatus
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "payments", indexes = [Index(name = "idx_payments_client_id", columnList = "client_id")])
class PaymentEntity(
    @Id
    var id: UUID,

    @Column(name = "client_id", nullable = false)
    var clientId: UUID,

    @Column(nullable = false, precision = 19, scale = 2)
    var amount: BigDecimal,

    @Column(nullable = false)
    var currency: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: PaymentStatus,

    @Column(nullable = false)
    var createdAt: Instant
)