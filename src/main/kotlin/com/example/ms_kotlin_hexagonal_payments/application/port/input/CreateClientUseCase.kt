package com.example.ms_kotlin_hexagonal_payments.application.port.input

import com.example.ms_kotlin_hexagonal_payments.domain.model.Client
import java.time.LocalDate
import java.util.UUID

interface CreateClientUseCase {

    fun create(name: String, document: String, email: String, phone: String, birthDate: LocalDate): Client

    fun findById(id: UUID): Client

    fun update(id: UUID, name: String, email: String, phone: String, birthDate: LocalDate, active: Boolean): Client

    fun findAll(): List<Client>
}