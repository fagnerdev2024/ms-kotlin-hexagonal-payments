package com.example.ms_kotlin_hexagonal_payments.application.service

import com.example.ms_kotlin_hexagonal_payments.application.port.input.ListPaymentsUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import com.example.ms_kotlin_hexagonal_payments.domain.model.enuns.PaymentStatus
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class ListPaymentsService(
    private val paymentRepositoryPort: PaymentRepositoryPort
) : ListPaymentsUseCase {

    override fun list(clientId: UUID?): List<Payment> {
        return if (clientId != null) {
            paymentRepositoryPort.findByClientId(clientId)
        } else {
            paymentRepositoryPort.findAll()
        }
    }

    override fun execute(status: PaymentStatus?): List<Payment> {
        return if (status != null) {
            paymentRepositoryPort.findByStatus(status)
        } else {
            paymentRepositoryPort.findAll()
        }
    }
}