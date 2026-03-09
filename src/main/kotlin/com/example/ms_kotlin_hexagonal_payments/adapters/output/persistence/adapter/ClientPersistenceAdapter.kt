package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.adapter

import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.mapper.ClientMapper
import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.repository.SpringDataClientRepository
import com.example.ms_kotlin_hexagonal_payments.application.port.output.ClientRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.model.Client
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ClientPersistenceAdapter(
    private val springDataClientRepository: SpringDataClientRepository
) : ClientRepositoryPort {

    override fun save(client: Client): Client {
        val clientEntity = ClientMapper.toEntity(client)
        return ClientMapper.toDomain(springDataClientRepository.save(clientEntity))
    }


    override fun findById(id: UUID): Client? {
        return springDataClientRepository.findById(id)
            .map(ClientMapper::toDomain)
            .orElse(null)
    }


    override fun existsByDocument(document: String): Boolean {
        return springDataClientRepository.existsByDocument(document)
    }


    override fun findAll(): List<Client> {
        return springDataClientRepository.findAll().map(ClientMapper::toDomain)
    }
}