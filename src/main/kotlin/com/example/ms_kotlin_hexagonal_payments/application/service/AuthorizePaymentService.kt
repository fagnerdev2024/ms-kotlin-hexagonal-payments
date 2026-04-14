package com.example.ms_kotlin_hexagonal_payments.application.service

import com.example.ms_kotlin_hexagonal_payments.application.port.input.AuthorizePaymentUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentAuthorizationPort
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.exception.NotFoundException
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Serviço de aplicação para autorizar pagamentos.
 * Implementa a lógica de negócio para validação e autorização de pagamentos,
 * integrando com a porta de autorização para processar a solicitação.
 */
@Service
class AuthorizePaymentService(
    private val paymentRepository: PaymentRepositoryPort,
    private val authorizationPort: PaymentAuthorizationPort
) : AuthorizePaymentUseCase {

    /**
     * Autoriza um pagamento específico através de um gateway externo.
     *
     * @param paymentId o UUID do pagamento a ser autorizado
     * @return o Payment autorizado e persistido com o resultado da autorização
     * @throws NotFoundException se o pagamento com o paymentId fornecido não existir
     */
    override fun authorize(paymentId: UUID): Payment {
        val payment = paymentRepository.findById(paymentId)
            ?: throw NotFoundException("Payment not found: $paymentId")

        val result = authorizationPort.authorize(payment)
        val updated = payment.authorize(result)

        return paymentRepository.save(updated)
    }
}