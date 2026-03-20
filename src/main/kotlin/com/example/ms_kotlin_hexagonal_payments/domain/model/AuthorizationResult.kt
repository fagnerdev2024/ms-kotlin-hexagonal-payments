package com.example.ms_kotlin_hexagonal_payments.domain.model

import com.example.ms_kotlin_hexagonal_payments.domain.model.enuns.PaymentStatus


data class AuthorizationResult(
    val status: PaymentStatus,
    val authorizationCode: String? = null,
    val declineReason: String? = null
)