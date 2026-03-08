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

    override fun save(client: Client): Client = ClientMapper.toDomain(springDataClientRepository.save(ClientMapper.toEntity(client)))

    override fun findById(id: UUID): Client? = springDataClientRepository.findById(id).map { ClientMapper.toDomain(it) }.orElse(null)

    override fun existsByDocument(document: String): Boolean = springDataClientRepository.existsByDocument(document)
}