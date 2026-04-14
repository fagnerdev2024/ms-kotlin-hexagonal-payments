package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.repository


import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity.ClientEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

/**
 * Interface de repositório para operações com ClientEntity usando Spring Data JPA.
 * Fornece operações CRUD padrão e consultas customizadas para dados de cliente.
 */
interface SpringDataClientRepository : JpaRepository<ClientEntity, UUID> {

    /**
     * Verifica se um cliente existe pelo número do documento.
     * @param document a string do documento a ser pesquisada
     * @return true se um cliente com o documento fornecido existir, false caso contrário
     */
    fun existsByDocument(document: String): Boolean
}