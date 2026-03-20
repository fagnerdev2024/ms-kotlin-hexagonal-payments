package com.example.ms_kotlin_hexagonal_payments.application.port.input


import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import com.example.ms_kotlin_hexagonal_payments.domain.model.enuns.PaymentStatus
import java.util.UUID

interface ListPaymentsUseCase {

    fun list(clientId: UUID? = null): List<Payment>

    fun execute(status: PaymentStatus?): List<Payment>
}