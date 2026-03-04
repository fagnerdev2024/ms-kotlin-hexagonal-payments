package com.example.ms_kotlin_hexagonal_payments.application.port.input


import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import java.math.BigDecimal
import java.util.UUID

interface CreatePaymentUseCase {

    fun create(clientId: UUID, amount: BigDecimal, currency: String = "BRL"): Payment
}