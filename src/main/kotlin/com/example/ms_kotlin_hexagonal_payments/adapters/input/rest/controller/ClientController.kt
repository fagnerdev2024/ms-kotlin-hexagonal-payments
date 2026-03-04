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
    fun create(@RequestBody @Valid req: CreateClientRequest): ClientResponse {
        val created = createClientUseCase.create(req.name, req.document)

        return ClientResponse(
            id = created.id,
            name = created.name,
            document = created.document
        )
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: UUID): ClientResponse {
        val client = createClientUseCase.findById(id)
        return ClientResponse(id = client.id, name = client.name, document = client.document)
    }
}