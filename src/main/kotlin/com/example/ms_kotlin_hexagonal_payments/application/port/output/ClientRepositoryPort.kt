package com.example.ms_kotlin_hexagonal_payments.application.port.output

import com.example.ms_kotlin_hexagonal_payments.domain.model.Client
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface ClientRepositoryPort {

    fun save(client: Client): Client

    fun findById(id: UUID): Client?

    fun existsByDocument(document: String): Boolean

    fun findAll(pageable: Pageable): Page<Client>
}