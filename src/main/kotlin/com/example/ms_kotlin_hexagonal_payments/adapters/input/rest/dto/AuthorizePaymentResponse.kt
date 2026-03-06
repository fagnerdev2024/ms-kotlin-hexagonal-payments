package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto




import com.example.ms_kotlin_hexagonal_payments.domain.model.PaymentStatus
import java.util.UUID

data class AuthorizePaymentResponse(
    val id: UUID,
    val status: PaymentStatus,
    val authorizationCode: String? = null,
    val declineReason: String? = null
)
