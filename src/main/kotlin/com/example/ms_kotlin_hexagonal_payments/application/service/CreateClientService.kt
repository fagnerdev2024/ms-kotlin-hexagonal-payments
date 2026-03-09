package com.example.ms_kotlin_hexagonal_payments.application.service

import com.example.ms_kotlin_hexagonal_payments.application.port.input.CreateClientUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.ClientRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.exception.BusinessRuleException
import com.example.ms_kotlin_hexagonal_payments.domain.exception.NotFoundException
import com.example.ms_kotlin_hexagonal_payments.domain.model.Client
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

@Service
class CreateClientService(
    private val clientRepositoryPort: ClientRepositoryPort
) : CreateClientUseCase {

    override fun create(name: String, document: String, email: String, phone: String, birthDate: LocalDate): Client {
        if (clientRepositoryPort.existsByDocument(document)) {
            throw BusinessRuleException("Client document already registered")
        }

        val client = Client(
            name = name,
            document = document,
            email = email,
            phone = phone,
            birthDate = birthDate
        )

        return clientRepositoryPort.save(client)
    }

    override fun findById(id: UUID): Client {
        return clientRepositoryPort.findById(id)
            ?: throw NotFoundException("Client not found: $id")
    }

    // NOVO
    override fun update(id: UUID, name: String, email: String, phone: String, birthDate: LocalDate, active: Boolean): Client {
        val existingClient = clientRepositoryPort.findById(id)
            ?: throw NotFoundException("Client not found: $id")

        val updatedClient = existingClient.copy(
            name = name,
            email = email,
            phone = phone,
            birthDate = birthDate,
            active = active,
            updatedAt = Instant.now()
        )

        return clientRepositoryPort.save(updatedClient)
    }

    override fun findAll(): List<Client> {
        return clientRepositoryPort.findAll()
    }
}