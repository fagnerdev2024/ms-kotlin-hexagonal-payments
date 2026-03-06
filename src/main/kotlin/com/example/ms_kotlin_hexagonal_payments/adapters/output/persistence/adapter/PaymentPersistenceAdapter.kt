package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.adapter


import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.mapper.PaymentMapper
import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.repository.SpringDataPaymentRepository
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PaymentPersistenceAdapter(
    private val repo: SpringDataPaymentRepository
) : PaymentRepositoryPort {

    override fun save(payment: Payment): Payment =
        PaymentMapper.toDomain(repo.save(PaymentMapper.toEntity(payment)))

    override fun findById(id: UUID): Payment? =
        repo.findById(id).map { PaymentMapper.toDomain(it) }.orElse(null)
}