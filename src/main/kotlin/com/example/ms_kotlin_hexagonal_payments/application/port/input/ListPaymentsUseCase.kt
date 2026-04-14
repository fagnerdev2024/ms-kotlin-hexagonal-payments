package com.example.ms_kotlin_hexagonal_payments.application.port.input


import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import com.example.ms_kotlin_hexagonal_payments.domain.model.enuns.PaymentStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface ListPaymentsUseCase {

    fun list(clientId: UUID?, pageable: Pageable): Page<Payment>

    fun listByStatus(status: PaymentStatus?, pageable: Pageable): Page<Payment>
}