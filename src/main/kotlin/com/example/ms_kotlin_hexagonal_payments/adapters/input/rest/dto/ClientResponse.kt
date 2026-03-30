package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto

import java.time.Instant
import java.time.LocalDate
import java.util.UUID

data class ClientResponse(
    val id: UUID,
    val name: String,
    val document: String,
    val email: String,
    val phone: String,
    val birthDate: LocalDate,
    val active: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant
)
