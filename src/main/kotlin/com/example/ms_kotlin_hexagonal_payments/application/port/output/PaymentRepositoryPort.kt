package com.example.ms_kotlin_hexagonal_payments.application.port.output

import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import com.example.ms_kotlin_hexagonal_payments.domain.model.enuns.PaymentStatus
import java.util.UUID


interface PaymentRepositoryPort {

    fun save(payment: Payment): Payment

    fun findById(id: UUID): Payment?

    fun findAll(): List<Payment>

    fun findByClientId(clientId: UUID): List<Payment>

    fun findByStatus(status: PaymentStatus): List<Payment>
}