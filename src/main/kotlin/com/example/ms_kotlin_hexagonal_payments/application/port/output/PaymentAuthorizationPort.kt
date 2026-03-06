package com.example.ms_kotlin_hexagonal_payments.application.port.output

import com.example.ms_kotlin_hexagonal_payments.domain.model.AuthorizationResult
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment


interface PaymentAuthorizationPort {

    fun authorize(payment: Payment): AuthorizationResult
}