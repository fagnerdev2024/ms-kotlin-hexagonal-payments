package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.adapter

import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.mapper.PaymentMapper
import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.repository.SpringDataPaymentRepository
import com.example.ms_kotlin_hexagonal_payments.application.port.output.LoadPaymentPort
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.application.port.output.SavePaymentPort
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import com.example.ms_kotlin_hexagonal_payments.domain.model.enuns.PaymentStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component
import java.util.UUID

/**
 * Adaptador de persistência para operações de pagamento.
 * Implementa as portas de saída da aplicação, traduzindo chamadas da lógica de negócio
 * para operações do Spring Data JPA, mantendo a aplicação desacoplada da tecnologia de persistência.
 */
@Component
class PaymentPersistenceAdapter(
    private val springDataPaymentRepository: SpringDataPaymentRepository
) : LoadPaymentPort, SavePaymentPort, PaymentRepositoryPort {

    /**
     * Busca um pagamento pelo seu ID.
     *
     * @param id o UUID do pagamento
     * @return o Payment encontrado ou null se não existir
     */
    override fun findById(id: UUID): Payment? {
        return springDataPaymentRepository.findById(id)
            .map(PaymentMapper::toDomain)
            .orElse(null)
    }

    /**
     * Persiste um pagamento no banco de dados.
     *
     * @param payment o Payment a ser salvo
     * @return o Payment salvo com ID gerado
     */
    override fun save(payment: Payment): Payment {
        val entity = PaymentMapper.toEntity(payment)
        return PaymentMapper.toDomain(springDataPaymentRepository.save(entity))
    }

    /**
     * Lista todos os pagamentos com paginação.
     *
     * @param pageable configurações de paginação
     * @return uma página contendo os pagamentos
     */
    override fun findAll(pageable: Pageable): Page<Payment> {
        return springDataPaymentRepository.findAll(pageable).map(PaymentMapper::toDomain)
    }

    /**
     * Lista todos os pagamentos de um cliente com paginação.
     *
     * @param clientId o UUID do cliente
     * @param pageable configurações de paginação
     * @return uma página contendo os pagamentos do cliente
     */
    override fun findByClientId(clientId: UUID, pageable: Pageable): Page<Payment> {
        return springDataPaymentRepository.findByClientId(clientId, pageable).map(PaymentMapper::toDomain)
    }

    /**
     * Lista todos os pagamentos com um status específico com paginação.
     *
     * @param status o status do pagamento
     * @param pageable configurações de paginação
     * @return uma página contendo os pagamentos com o status informado
     */
    override fun findByStatus(status: PaymentStatus, pageable: Pageable): Page<Payment> {
        return springDataPaymentRepository.findByStatus(status, pageable)
            .map(PaymentMapper::toDomain)
    }
}