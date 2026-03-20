package com.example.ms_kotlin_hexagonal_payments.adapters.output.gateway

import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentAuthorizationPort
import com.example.ms_kotlin_hexagonal_payments.domain.model.AuthorizationResult
import com.example.ms_kotlin_hexagonal_payments.domain.model.Payment
import com.example.ms_kotlin_hexagonal_payments.domain.model.enuns.PaymentStatus
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class FakePaymentAuthorizationAdapter : PaymentAuthorizationPort {

    override fun authorize(payment: Payment): AuthorizationResult {
        // regra simples pra simular:
        // pagamentos <= 100 aprovam, > 100 recusam
        return if (payment.amount.amount.toDouble() <= 100.0) {
            AuthorizationResult(
                status = PaymentStatus.APPROVED,
                authorizationCode = "AUTH-${UUID.randomUUID().toString().take(8)}"
            )
        } else {
            AuthorizationResult(
                status = PaymentStatus.DECLINED,
                declineReason = "INSUFFICIENT_FUNDS"
            )
        }
    }
}