package com.example.ms_kotlin_hexagonal_payments.application.port.input


import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import java.util.UUID

interface FindPaymentUseCase {

    fun findById(id: UUID): Payment
}