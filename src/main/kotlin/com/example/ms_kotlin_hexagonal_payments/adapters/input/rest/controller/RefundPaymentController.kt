package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.controller


import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.RefundPaymentRequest
import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.RefundPaymentResponse
import com.example.ms_kotlin_hexagonal_payments.application.port.input.RefundPaymentCommand
import com.example.ms_kotlin_hexagonal_payments.application.port.input.RefundPaymentUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/payments")
class RefundPaymentController(
    private val refundPaymentUseCase: RefundPaymentUseCase
) {

    @PostMapping("/{paymentId}/refunds")
    @ResponseStatus(HttpStatus.CREATED)
    fun refund(@PathVariable paymentId: UUID, @Valid @RequestBody request: RefundPaymentRequest): RefundPaymentResponse {
        val result = refundPaymentUseCase.execute(RefundPaymentCommand(paymentId = paymentId, amount = request.amount, reason = request.reason))
        return RefundPaymentResponse(refundId = result.refundId, paymentId = result.paymentId, status = result.status, refundedAmount = result.refundedAmount, reason = result.reason)
    }
}