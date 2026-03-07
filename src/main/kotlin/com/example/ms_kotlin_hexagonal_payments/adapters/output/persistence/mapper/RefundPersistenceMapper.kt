package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.mapper

import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity.RefundEntity
import com.example.ms_kotlin_hexagonal_payments.domain.model.Refund

object RefundPersistenceMapper {

    fun toEntity(refund: Refund): RefundEntity {
        return RefundEntity(
            id = refund.id!!,
            paymentId = refund.paymentId,
            amount = refund.amount,
            reason = refund.reason,
            status = refund.status,
            createdAt = refund.createdAt
        )
    }

    fun toDomain(entity: RefundEntity): Refund {
        return Refund(
            id = entity.id,
            paymentId = entity.paymentId,
            amount = entity.amount,
            reason = entity.reason,
            status = entity.status,
            createdAt = entity.createdAt
        )
    }
}