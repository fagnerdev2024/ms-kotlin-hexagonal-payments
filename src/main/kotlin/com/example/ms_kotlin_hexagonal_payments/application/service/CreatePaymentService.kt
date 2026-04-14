package com.example.ms_kotlin_hexagonal_payments.application.service


import com.example.ms_kotlin_hexagonal_payments.application.port.input.CreatePaymentUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.ClientRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.exception.NotFoundException
import com.example.ms_kotlin_hexagonal_payments.domain.model.Money
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

/**
 * Serviço de aplicação para criar novos pagamentos.
 * Implementa a lógica de negócio para validação e criação de pagamentos,
 * garantindo que o cliente existe antes de criar um pagamento.
 */
@Service
class CreatePaymentService(
    private val clientRepository: ClientRepositoryPort,
    private val paymentRepository: PaymentRepositoryPort
) : CreatePaymentUseCase {

    /**
     * Cria um novo pagamento para um cliente específico.
     *
     * @param clientId o UUID do cliente para o qual o pagamento será criado
     * @param amount o valor do pagamento
     * @param currency a moeda do pagamento
     * @return o Payment criado e persistido
     * @throws NotFoundException se o cliente com o clientId fornecido não existir
     */
    override fun create(clientId: UUID, amount: BigDecimal, currency: String): Payment {
        val client = clientRepository.findById(clientId)
            ?: throw NotFoundException("Client not found: $clientId")

        val payment = Payment(
            clientId = client.id,
            amount = Money(amount = amount, currency = currency).normalized()
        )

        return paymentRepository.save(payment)
    }
}