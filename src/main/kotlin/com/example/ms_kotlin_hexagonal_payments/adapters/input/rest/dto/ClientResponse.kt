package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto



import java.util.UUID

data class ClientResponse(
    val id: UUID,
    val name: String,
    val document: String
)
