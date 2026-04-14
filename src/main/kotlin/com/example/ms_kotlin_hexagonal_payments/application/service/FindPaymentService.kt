package com.example.ms_kotlin_hexagonal_payments.application.service

import com.example.ms_kotlin_hexagonal_payments.application.port.input.FindPaymentUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.exception.NotFoundException
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Serviço de aplicação para buscar pagamentos.
 * Implementa a lógica de negócio para recuperação de pagamentos específicos,
 * garantindo que o pagamento existe antes de retorná-lo.
 */
@Service
class FindPaymentService(
    private val paymentRepository: PaymentRepositoryPort
) : FindPaymentUseCase {

    /**
     * Busca um pagamento específico pelo seu identificador único.
     *
     * @param id o UUID do pagamento a ser buscado
     * @return o Payment encontrado
     * @throws NotFoundException se o pagamento com o id fornecido não existir
     */
    override fun findById(id: UUID): Payment {
        return paymentRepository.findById(id)
            ?: throw NotFoundException("Payment not found: $id")
    }
}