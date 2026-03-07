package com.example.ms_kotlin_hexagonal_payments.application.service


import com.example.ms_kotlin_hexagonal_payments.application.port.input.RefundPaymentCommand
import com.example.ms_kotlin_hexagonal_payments.application.port.input.RefundPaymentResult
import com.example.ms_kotlin_hexagonal_payments.application.port.input.RefundPaymentUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.LoadPaymentPort
import com.example.ms_kotlin_hexagonal_payments.application.port.output.PaymentRefundPort
import com.example.ms_kotlin_hexagonal_payments.application.port.output.RefundGatewayCommand
import com.example.ms_kotlin_hexagonal_payments.application.port.output.SavePaymentPort
import com.example.ms_kotlin_hexagonal_payments.application.port.output.SaveRefundPort
import com.example.ms_kotlin_hexagonal_payments.domain.exception.BusinessException
import com.example.ms_kotlin_hexagonal_payments.domain.model.Refund
import com.example.ms_kotlin_hexagonal_payments.domain.model.RefundStatus
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RefundPaymentService(
    private val loadPaymentPort: LoadPaymentPort,
    private val savePaymentPort: SavePaymentPort,
    private val saveRefundPort: SaveRefundPort,
    private val paymentRefundPort: PaymentRefundPort
) : RefundPaymentUseCase {

    override fun execute(command: RefundPaymentCommand): RefundPaymentResult {
        val payment = loadPaymentPort.findById(command.paymentId)
            ?: throw BusinessException("Pagamento não encontrado")

        // REGRA 1
        payment.validateRefundEligibility(command.amount)

        val gatewayResult = paymentRefundPort.refund(
            RefundGatewayCommand(
                paymentId = command.paymentId,
                amount = command.amount,
                reason = command.reason
            )
        )

        if (!gatewayResult.success) {
            val failedRefund = saveRefundPort.save(
                Refund(
                    id = UUID.randomUUID(),
                    paymentId = command.paymentId,
                    amount = command.amount,
                    reason = command.reason,
                    status = RefundStatus.FAILED
                )
            )

            throw BusinessException(
                gatewayResult.message ?: "Falha ao processar estorno no gateway"
            )
        }

        payment.markAsRefunded()
        savePaymentPort.save(payment)

        val savedRefund = saveRefundPort.save(
            Refund(
                id = UUID.randomUUID(),
                paymentId = command.paymentId,
                amount = command.amount,
                reason = command.reason,
                status = RefundStatus.PROCESSED
            )
        )

        return RefundPaymentResult(
            refundId = savedRefund.id!!,
            paymentId = savedRefund.paymentId,
            status = savedRefund.status.name,
            refundedAmount = savedRefund.amount,
            reason = savedRefund.reason
        )
    }
}