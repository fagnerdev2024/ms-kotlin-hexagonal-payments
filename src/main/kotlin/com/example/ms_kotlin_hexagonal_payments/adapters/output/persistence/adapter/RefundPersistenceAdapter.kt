package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.adapter

import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.mapper.RefundPersistenceMapper
import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.repository.SpringDataRefundRepository
import com.example.ms_kotlin_hexagonal_payments.application.port.output.SaveRefundPort
import com.example.ms_kotlin_hexagonal_payments.domain.model.Refund
import org.springframework.stereotype.Component

@Component
class RefundPersistenceAdapter(
    private val repository: SpringDataRefundRepository
) : SaveRefundPort {

    override fun save(refund: Refund): Refund {
        val entity = RefundPersistenceMapper.toEntity(refund)
        return RefundPersistenceMapper.toDomain(repository.save(entity))
    }
}