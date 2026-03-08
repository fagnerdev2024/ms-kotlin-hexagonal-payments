package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto



import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import java.time.LocalDate

data class CreateClientRequest(
    @field:NotBlank(message = "name is required")
    val name: String,

    @field:NotBlank(message = "document is required")
    val document: String,

    @field:NotBlank(message = "email is required")
    @field:Email(message = "email must be valid")
    val email: String,

    @field:NotBlank(message = "phone is required")
    val phone: String,

    @field:Past(message = "birthDate must be in the past")
    val birthDate: LocalDate
)
