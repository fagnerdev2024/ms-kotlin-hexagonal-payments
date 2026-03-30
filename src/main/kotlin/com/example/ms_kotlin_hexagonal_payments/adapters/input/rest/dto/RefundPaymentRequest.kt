package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class RefundPaymentRequest(
    @field:NotNull
    @field:DecimalMin(value = "0.01")
    val amount: BigDecimal,

    @field:NotBlank
    val reason: String
)
