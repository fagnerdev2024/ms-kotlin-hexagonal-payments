package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.repository

import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity.PaymentEntity
import com.example.ms_kotlin_hexagonal_payments.domain.model.enuns.PaymentStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

/**
 * Interface de repositório para operações com PaymentEntity usando Spring Data JPA.
 * Fornece operações CRUD padrão e consultas customizadas para dados de pagamento.
 */
interface SpringDataPaymentRepository : JpaRepository<PaymentEntity, UUID> {

    /**
     * Encontra todos os pagamentos associados a um cliente específico com paginação.
     * @param clientId o UUID do cliente
     * @param pageable configurações de paginação
     * @return página de PaymentEntity do cliente
     */
    fun findByClientId(clientId: UUID, pageable: Pageable): Page<PaymentEntity>

    /**
     * Encontra todos os pagamentos com um status específico com paginação.
     * @param status o status do pagamento a ser filtrado
     * @param pageable configurações de paginação
     * @return página de PaymentEntity com o status fornecido
     */
    fun findByStatus(status: PaymentStatus, pageable: Pageable): Page<PaymentEntity>
}