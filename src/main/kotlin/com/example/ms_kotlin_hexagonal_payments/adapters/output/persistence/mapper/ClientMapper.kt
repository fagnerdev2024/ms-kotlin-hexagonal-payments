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

    fun toDomain(entity: ClientEntity): Client {
        return Client(
            id = entity.id,
            name = entity.name,
            document = entity.document,
            email = entity.email,
            phone = entity.phone,
            birthDate = entity.birthDate,
            active = entity.active,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }
}