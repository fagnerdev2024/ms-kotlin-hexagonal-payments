package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.controller


import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.CreatePaymentRequest
import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.PaymentResponse
import com.example.ms_kotlin_hexagonal_payments.application.port.input.CreatePaymentUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/payments")
class PaymentController(
    private val createPaymentUseCase: CreatePaymentUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid req: CreatePaymentRequest): PaymentResponse {
        val payment = createPaymentUseCase.create(req.clientId, req.amount, req.currency)
        return PaymentResponse(
            id = payment.id,
            clientId = payment.clientId,
            amount = payment.amount.amount,
            currency = payment.amount.currency,
            status = payment.status,
            createdAt = payment.createdAt
        )
    }
}