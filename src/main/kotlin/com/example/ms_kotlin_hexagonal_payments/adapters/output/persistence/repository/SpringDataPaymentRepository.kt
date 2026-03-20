package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.repository

import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity.PaymentEntity
import com.example.ms_kotlin_hexagonal_payments.domain.model.enuns.PaymentStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SpringDataPaymentRepository : JpaRepository<PaymentEntity, UUID> {

    fun findByClientId(clientId: UUID): List<PaymentEntity>

    fun findByStatus(status: PaymentStatus): List<PaymentEntity>
}