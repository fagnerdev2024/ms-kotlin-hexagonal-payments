package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity



import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "clients", indexes = [Index(name = "idx_clients_document", columnList = "document", unique = true)])
class ClientEntity(
    @Id
    var id: UUID,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var document: String
)