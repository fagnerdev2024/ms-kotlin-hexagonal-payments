package com.example.ms_kotlin_hexagonal_payments.application.service

import com.example.ms_kotlin_hexagonal_payments.application.port.input.ClientUseCase
import com.example.ms_kotlin_hexagonal_payments.application.port.output.ClientRepositoryPort
import com.example.ms_kotlin_hexagonal_payments.domain.exception.BusinessRuleException
import com.example.ms_kotlin_hexagonal_payments.domain.exception.NotFoundException
import com.example.ms_kotlin_hexagonal_payments.domain.model.Client
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

/**
 * Serviço de aplicação para gerenciar operações de cliente.
 * Implementa a lógica de negócio para criação, atualização, busca e listagem de clientes,
 * garantindo validações de regras de negócio e integridade dos dados.
 */
@Service
class ClientService(
    private val clientRepositoryPort: ClientRepositoryPort
) : ClientUseCase {

    /**
     * Cria um novo cliente com as informações fornecidas.
     * Valida se o documento já não está registrado antes de criar.
     *
     * @param name o nome completo do cliente
     * @param document o número do documento (único)
     * @param email o endereço de email do cliente
     * @param phone o número de telefone do cliente
     * @param birthDate a data de nascimento do cliente
     * @return o Client criado e persistido
     * @throws BusinessRuleException se o documento já estiver registrado
     */
    override fun create(name: String, document: String, email: String, phone: String, birthDate: LocalDate): Client {
        if (clientRepositoryPort.existsByDocument(document)) {
            throw BusinessRuleException("Client document already registered")
        }

        val client = Client(
            name = name,
            document = document,
            email = email,
            phone = phone,
            birthDate = birthDate
        )

        return clientRepositoryPort.save(client)
    }

    /**
     * Busca um cliente específico pelo seu identificador único.
     *
     * @param id o UUID do cliente a ser buscado
     * @return o Client encontrado
     * @throws NotFoundException se o cliente com o id fornecido não existir
     */
    override fun findById(id: UUID): Client {
        return clientRepositoryPort.findById(id)
            ?: throw NotFoundException("Client not found: $id")
    }

    /**
     * Atualiza as informações de um cliente existente.
     * Preserva o documento original e atualiza os demais campos.
     *
     * @param id o UUID do cliente a ser atualizado
     * @param name o novo nome do cliente
     * @param email o novo email do cliente
     * @param phone o novo telefone do cliente
     * @param birthDate a nova data de nascimento do cliente
     * @param active o novo status de atividade do cliente
     * @return o Client atualizado e persistido
     * @throws NotFoundException se o cliente com o id fornecido não existir
     */
    override fun update(id: UUID, name: String, email: String, phone: String, birthDate: LocalDate, active: Boolean): Client {
        val existingClient = clientRepositoryPort.findById(id)
            ?: throw NotFoundException("Client not found: $id")

        val updatedClient = existingClient.copy(
            name = name,
            email = email,
            phone = phone,
            birthDate = birthDate,
            active = active,
            updatedAt = Instant.now()
        )

        return clientRepositoryPort.save(updatedClient)
    }

    /**
     * Lista todos os clientes com suporte a paginação.
     *
     * @param pageable configurações de paginação (página, tamanho, ordenação)
     * @return uma página contendo os clientes
     */
    override fun findAll(pageable: Pageable): Page<Client> {
        return clientRepositoryPort.findAll(pageable)
    }
}
