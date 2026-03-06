package com.example.ms_kotlin_hexagonal_payments.application.service

import com.example.ms_kotlin_hexagonal_payments.application.port.input.AuthorizePaymentUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentAuthorizationPort
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.exception.NotFoundException
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthorizePaymentService(
    private val paymentRepository: PaymentRepositoryPort,
    private val authorizationPort: PaymentAuthorizationPort
) : AuthorizePaymentUseCase {

    override fun authorize(paymentId: UUID): Payment {
        val payment = paymentRepository.findById(paymentId)
            ?: throw NotFoundException("Payment not found: $paymentId")

        val result = authorizationPort.authorize(payment)
        val updated = payment.authorize(result)

        return paymentRepository.save(updated)
    }
}