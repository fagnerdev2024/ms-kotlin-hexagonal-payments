package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.mapper

import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity.PaymentEntity
import com.example.ms_kotlin_hexagonal_payments.domain.model.Money
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment


object PaymentMapper {
    fun toEntity(domain: Payment) = PaymentEntity(
        id = domain.id,
        clientId = domain.clientId,
        amount = domain.amount.amount,
        currency = domain.amount.currency,
        status = domain.status,
        createdAt = domain.createdAt,
        authorizationCode = domain.authorizationCode,
        declineReason = domain.declineReason
    )

    fun toDomain(paymentEntity: PaymentEntity) = Payment(
        id = paymentEntity.id,
        clientId = paymentEntity.clientId,
        amount = Money(paymentEntity.amount, paymentEntity.currency),
        status = paymentEntity.status,
        createdAt = paymentEntity.createdAt,
        authorizationCode = paymentEntity.authorizationCode,
        declineReason = paymentEntity.declineReason
    )
}