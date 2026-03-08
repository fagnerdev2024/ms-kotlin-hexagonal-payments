package com.example.ms_kotlin_hexagonal_payments.application.service

import com.example.ms_kotlin_hexagonal_payments.application.port.input.ListPaymentsUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class ListPaymentsService(
    private val paymentRepository: PaymentRepositoryPort
) : ListPaymentsUseCase {

    override fun list(clientId: UUID?): List<Payment> {
        return if (clientId != null) {
            paymentRepository.findByClientId(clientId)
        } else {
            paymentRepository.findAll()
        }
    }
}