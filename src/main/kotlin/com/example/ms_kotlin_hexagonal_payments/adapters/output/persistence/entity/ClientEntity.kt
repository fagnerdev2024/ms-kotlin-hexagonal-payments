package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity



import jakarta.persistence.*
import java.time.Instant
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "clients", indexes = [Index(name = "idx_clients_document", columnList = "document", unique = true)])
class ClientEntity(
    @Id
    val id: UUID,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val document: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val phone: String,

    @Column(nullable = false)
    val birthDate: LocalDate,

    @Column(nullable = false)
    val active: Boolean,

    @Column(nullable = false)
    val createdAt: Instant,

    @Column(nullable = false)
    val updatedAt: Instant
)