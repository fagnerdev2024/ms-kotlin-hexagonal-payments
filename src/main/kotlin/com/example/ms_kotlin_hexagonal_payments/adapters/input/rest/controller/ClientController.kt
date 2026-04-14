package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.controller

import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.ClientResponse
import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.CreateClientRequest
import com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.dto.UpdateClientRequest
import com.example.ms_kotlin_hexagonal_payments.application.port.input.ClientUseCase
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/v1/clients")
class ClientController(
    private val clientUseCase: ClientUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid createClientRequest: CreateClientRequest): ClientResponse {
        val created = clientUseCase.create(
            name = createClientRequest.name,
            document = createClientRequest.document,
            email = createClientRequest.email,
            phone = createClientRequest.phone,
            birthDate = createClientRequest.birthDate
        )

        return toClientResponse(created)
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: UUID): ClientResponse {
        val client = clientUseCase.findById(id)

        return toClientResponse(client)
    }


    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody @Valid updateClientRequest: UpdateClientRequest): ClientResponse {
        val updated = clientUseCase.update(
            id = id,
            name = updateClientRequest.name,
            email = updateClientRequest.email,
            phone = updateClientRequest.phone,
            birthDate = updateClientRequest.birthDate,
            active = updateClientRequest.active
        )

        return toClientResponse(updated)
    }


    @GetMapping
    fun findAll(pageable: Pageable): Page<ClientResponse> {
        return clientUseCase.findAll(pageable).map { client -> toClientResponse(client) }
    }

    private fun toClientResponse(client: com.example.ms_kotlin_hexagonal_payments.domain.model.Client): ClientResponse {
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