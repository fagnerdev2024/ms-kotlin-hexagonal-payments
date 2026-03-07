package com.example.ms_kotlin_hexagonal_payments.application.port.output


import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import java.util.UUID

interface LoadPaymentPort {
    fun findById(paymentId: UUID): Payment?
}