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

@Service
class CreatePaymentService(private val clientRepository: ClientRepositoryPort, private val paymentRepository: PaymentRepositoryPort) : CreatePaymentUseCase {

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