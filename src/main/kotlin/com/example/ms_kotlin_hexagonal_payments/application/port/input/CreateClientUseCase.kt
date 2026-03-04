package com.example.ms_kotlin_hexagonal_payments.application.port.input

import com.example.ms_kotlin_hexagonal_payments.domain.model.Client
import java.util.UUID

interface CreateClientUseCase {

    fun create(name: String, document: String): Client

    fun findById(id: UUID): Client
}