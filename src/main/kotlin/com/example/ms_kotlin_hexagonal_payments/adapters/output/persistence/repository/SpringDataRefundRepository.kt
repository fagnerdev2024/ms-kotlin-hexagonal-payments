package com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.repository




import com.example.ms_kotlin_hexagonal_payments.adapters.output.persistence.entity.RefundEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SpringDataRefundRepository : JpaRepository<RefundEntity, UUID>