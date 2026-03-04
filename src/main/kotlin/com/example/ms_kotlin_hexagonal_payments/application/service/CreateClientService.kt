package com.example.ms_kotlin_hexagonal_payments.application.service


import com.example.ms_kotlin_hexagonal_payments.application.port.input.CreateClientUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.ClientRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.exception.BusinessRuleException
import com.example.ms_kotlin_hexagonal_payments.domain.exception.NotFoundException
import com.example.ms_kotlin_hexagonal_payments.domain.model.Client
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CreateClientService(
    private val clientRepository: ClientRepositoryPort
) : CreateClientUseCase {

    override fun create(name: String, document: String): Client {
        if (clientRepository.existsByDocument(document)) {
            throw BusinessRuleException("Client document already registered")
        }

        val client = Client(name = name, document = document)
        return clientRepository.save(client)
    }

    override fun findById(id: UUID): Client {
        return clientRepository.findById(id)
            ?: throw NotFoundException("Client not found: $id")
    }
}