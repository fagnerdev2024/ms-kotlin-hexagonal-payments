package com.example.ms_kotlin_hexagonal_payments.domain.model

import java.time.Instant
import java.time.LocalDate
import java.util.UUID

data class Client(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val document: String,
    val email: String,
    val phone: String,
    val birthDate: LocalDate,
    val active: Boolean = true,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
        require(document.isNotBlank()) { "document must not be blank" }
    }
}
