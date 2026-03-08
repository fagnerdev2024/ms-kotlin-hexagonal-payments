package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.mapper


import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity.ClientEntity
import com.example.ms_kotlin_hexagonal_payments.domain.model.Client

object ClientMapper {

    fun toEntity(client: Client): ClientEntity {
        return ClientEntity(
            id = client.id,
            name = client.name,
            document = client.document,
            email = client.email,
            phone = client.phone,
            birthDate = client.birthDate,
            active = client.active,
            createdAt = client.createdAt,
            updatedAt = client.updatedAt
        )
    }

    fun toDomain(clientEntity: ClientEntity): Client {
        return Client(
            id = clientEntity.id,
            name = clientEntity.name,
            document = clientEntity.document,
            email = clientEntity.email,
            phone = clientEntity.phone,
            birthDate = clientEntity.birthDate,
            active = clientEntity.active,
            createdAt = clientEntity.createdAt,
            updatedAt = clientEntity.updatedAt
        )
    }
}