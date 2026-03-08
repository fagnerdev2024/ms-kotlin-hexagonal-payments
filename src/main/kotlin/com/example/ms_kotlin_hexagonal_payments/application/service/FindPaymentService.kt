package com.example.ms_kotlin_hexagonal_payments.application.service

import com.example.ms_kotlin_hexagonal_payments.application.port.input.FindPaymentUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.exception.NotFoundException
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class FindPaymentService(
    private val paymentRepository: PaymentRepositoryPort
) : FindPaymentUseCase {

    override fun findById(id: UUID): Payment {
        return paymentRepository.findById(id)
            ?: throw NotFoundException("Payment not found: $id")
    }
}