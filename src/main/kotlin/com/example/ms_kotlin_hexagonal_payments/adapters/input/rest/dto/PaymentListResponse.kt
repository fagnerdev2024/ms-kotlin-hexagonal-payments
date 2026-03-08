package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto

data class PaymentListResponse(
    val items: List<PaymentResponse>,
    val total: Int
)
