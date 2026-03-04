package com.example.ms_kotlin_hexagonal_payments.domain.model

import java.util.UUID

data class Client(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val document: String // CPF/CNPJ (simples aqui)
) {
    init {
        require(name.isNotBlank()) { "name must not be blank" }
        require(document.isNotBlank()) { "document must not be blank" }
    }
}
