package com.example.ms_kotlin_hexagonal_payments.application.port.output

import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import com.example.ms_kotlin_hexagonal_payments.domain.model.enuns.PaymentStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID


interface PaymentRepositoryPort {

    fun save(payment: Payment): Payment

    fun findById(id: UUID): Payment?

    fun findAll(pageable: Pageable): Page<Payment>

    fun findByClientId(clientId: UUID, pageable: Pageable): Page<Payment>

    fun findByStatus(status: PaymentStatus, pageable: Pageable): Page<Payment>
}