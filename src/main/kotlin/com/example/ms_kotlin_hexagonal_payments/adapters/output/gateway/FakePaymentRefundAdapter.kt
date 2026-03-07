package com.example.ms_kotlin_hexagonal_payments.adapters.output.gateway



import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRefundPort
import com.example.ms_kotlin_hexagonal_payments.application.port.output.RefundGatewayCommand
import com.example.ms_kotlin_hexagonal_payments.application.port.output.RefundGatewayResult
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class FakePaymentRefundAdapter : PaymentRefundPort {

    override fun refund(command: RefundGatewayCommand): RefundGatewayResult {
        return RefundGatewayResult(
            success = true,
            externalId = UUID.randomUUID().toString(),
            message = "Refund processed successfully"
        )
    }
}