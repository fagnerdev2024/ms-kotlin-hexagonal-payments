package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto



import jakarta.validation.constraints.NotBlank

data class CreateClientRequest(
    @field:NotBlank val name: String,
    @field:NotBlank val document: String
)
