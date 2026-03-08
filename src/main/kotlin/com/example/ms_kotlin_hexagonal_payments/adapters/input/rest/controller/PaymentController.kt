package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.controller


import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.AuthorizePaymentResponse
import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.CreatePaymentRequest
import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.PaymentListResponse
import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.PaymentResponse
import com.example.ms_kotlin_hexagonal_payments.application.port.input.AuthorizePaymentUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.input.CreatePaymentUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.input.FindPaymentUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.input.ListPaymentsUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/v1/payments")
class PaymentController(
    private val createPaymentUseCase: CreatePaymentUseCase,
    private val authorizePaymentUseCase: AuthorizePaymentUseCase,
    private val findPaymentUseCase: FindPaymentUseCase,
    private val listPaymentsUseCase: ListPaymentsUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid createPaymentRequest: CreatePaymentRequest): PaymentResponse {
        val payment = createPaymentUseCase.create(createPaymentRequest.clientId, createPaymentRequest.amount, createPaymentRequest.currency)
        return PaymentResponse(
            id = payment.id,
            clientId = payment.clientId,
            amount = payment.amount.amount,
            currency = payment.amount.currency,
            status = payment.status,
            createdAt = payment.createdAt
        )
    }

    @PostMapping("/{id}/authorize")
    fun authorize(@PathVariable id: UUID): AuthorizePaymentResponse {
        val updated = authorizePaymentUseCase.authorize(id)

        return AuthorizePaymentResponse(
            id = updated.id,
            status = updated.status,
            authorizationCode = updated.authorizationCode,
            declineReason = updated.declineReason
        )
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: UUID): PaymentResponse {
        val payment = findPaymentUseCase.findById(id)
        return PaymentResponse(
            id = payment.id,
            clientId = payment.clientId,
            amount = payment.amount.amount,
            currency = payment.amount.currency,
            status = payment.status,
            createdAt = payment.createdAt
        )
    }

    @GetMapping
    fun list(@RequestParam(required = false) clientId: UUID?): PaymentListResponse {
        val payments = listPaymentsUseCase.list(clientId)
        val items = payments.map {
            PaymentResponse(
                id = it.id,
                clientId = it.clientId,
                amount = it.amount.amount,
                currency = it.amount.currency,
                status = it.status,
                createdAt = it.createdAt
            )
        }

        return PaymentListResponse(
            items = items,
            total = items.size
        )
    }
}