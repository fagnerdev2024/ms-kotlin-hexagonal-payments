package com.example.ms_kotlin_hexagonal_payments.application.port.output

import com.example.ms_kotlin_hexagonal_payments.domain.model.Refund


interface SaveRefundPort {
    fun save(refund: Refund): Refund
}