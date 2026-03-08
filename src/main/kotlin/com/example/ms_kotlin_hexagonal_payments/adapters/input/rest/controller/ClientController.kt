package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.controller

import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.ClientResponse
import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.CreateClientRequest
import com.example.ms_kotlin_hexagonal_payments.application.port.input.CreateClientUseCase
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/v1/clients")
class ClientController(
    private val createClientUseCase: CreateClientUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid createClientRequest: CreateClientRequest): ClientResponse {
        val created = createClientUseCase.create(
            name = createClientRequest.name,
            document = createClientRequest.document,
            email = createClientRequest.email,
            phone = createClientRequest.phone,
            birthDate = createClientRequest.birthDate
        )

        return ClientResponse(
            id = created.id,
            name = created.name,
            document = created.document,
            email = created.email,
            phone = created.phone,
            birthDate = created.birthDate,
            active = created.active,
            createdAt = created.createdAt,
            updatedAt = created.updatedAt
        )
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: UUID): ClientResponse {
        val client = createClientUseCase.findById(id)

        return ClientResponse(
            id = client.id,
            name = client.name,
            document = client.document,
            email = client.email,
            phone = client.phone,
            birthDate = client.birthDate,
            active = client.active,
            createdAt = client.createdAt,
            updatedAt = client.updatedAt
        )
    }
}