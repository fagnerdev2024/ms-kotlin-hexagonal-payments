package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.adapter

import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.mapper.PaymentMapper
import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.repository.SpringDataPaymentRepository
import com.example.ms_kotlin_hexagonal_payments.application.port.output.LoadPaymentPort
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.application.port.output.SavePaymentPort
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PaymentPersistenceAdapter(
    private val springDataPaymentRepository: SpringDataPaymentRepository
) : LoadPaymentPort, SavePaymentPort, PaymentRepositoryPort {

    override fun findById(paymentId: UUID): Payment? {
        return springDataPaymentRepository.findById(paymentId)
            .map(PaymentMapper::toDomain)
            .orElse(null)
    }

    override fun save(payment: Payment): Payment {
        val entity = PaymentMapper.toEntity(payment)
        return PaymentMapper.toDomain(springDataPaymentRepository.save(entity))
    }

    override fun findAll(): List<Payment> {
        return springDataPaymentRepository.findAll().map(PaymentMapper::toDomain)
    }

    override fun findByClientId(clientId: UUID): List<Payment> {
        return springDataPaymentRepository.findByClientId(clientId).map(PaymentMapper::toDomain)
    }
}