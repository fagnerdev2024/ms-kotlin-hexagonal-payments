package com.example.ms_kotlin_hexagonal_payments.domain.model



enum class RefundStatus {
    REQUESTED,
    REFUSED,
    PROCESSED,
    FAILED
}