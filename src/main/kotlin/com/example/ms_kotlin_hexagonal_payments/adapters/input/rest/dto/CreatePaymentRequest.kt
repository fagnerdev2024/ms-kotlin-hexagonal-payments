package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto



import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.util.UUID

data class CreatePaymentRequest(
    @field:NotNull val clientId: UUID,
    @field:NotNull @field:DecimalMin("0.00") val amount: BigDecimal,
    @field:NotBlank val currency: String = "BRL"
)
