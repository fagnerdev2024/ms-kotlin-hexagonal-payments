package com.example.ms_kotlin_hexagonal_payments.application.port.output

import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment


interface SavePaymentPort {
    fun save(payment: Payment): Payment
}