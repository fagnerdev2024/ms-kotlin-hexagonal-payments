package com.example.ms_kotlin_hexagonal_payments.application.service

import com.example.ms_kotlin_hexagonal_payments.application.port.input.ListPaymentsUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import com.example.ms_kotlin_hexagonal_payments.domain.model.enuns.PaymentStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Serviço de aplicação para listar pagamentos.
 * Implementa a lógica de negócio para listagem de pagamentos com filtros opcionais,
 * suportando paginação para escalabilidade.
 */
@Service
class ListPaymentsService(
    private val paymentRepositoryPort: PaymentRepositoryPort
) : ListPaymentsUseCase {

    /**
     * Lista pagamentos com filtro opcional por cliente e paginação.
     *
     * @param clientId o UUID do cliente para filtrar (opcional)
     * @param pageable configurações de paginação (página, tamanho, ordenação)
     * @return uma página contendo os pagamentos filtrados
     */
    override fun list(clientId: UUID?, pageable: Pageable): Page<Payment> {
        return if (clientId != null) {
            paymentRepositoryPort.findByClientId(clientId, pageable)
        } else {
            paymentRepositoryPort.findAll(pageable)
        }
    }

    /**
     * Lista pagamentos com filtro opcional por status e paginação.
     *
     * @param status o status do pagamento para filtrar (opcional)
     * @param pageable configurações de paginação (página, tamanho, ordenação)
     * @return uma página contendo os pagamentos filtrados
     */
    override fun listByStatus(status: PaymentStatus?, pageable: Pageable): Page<Payment> {
        return if (status != null) {
            paymentRepositoryPort.findByStatus(status, pageable)
        } else {
            paymentRepositoryPort.findAll(pageable)
        }
    }
}