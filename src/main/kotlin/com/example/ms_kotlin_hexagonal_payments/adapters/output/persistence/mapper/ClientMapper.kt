package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.mapper

import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity.ClientEntity
import com.example.ms_kotlin_hexagonal_payments.domain.model.Client


object ClientMapper {
    fun toEntity(domain: Client) = ClientEntity(
        id = domain.id,
        name = domain.name,
        document = domain.document
    )

    fun toDomain(entity: ClientEntity) = Client(
        id = entity.id,
        name = entity.name,
        document = entity.document
    )
}