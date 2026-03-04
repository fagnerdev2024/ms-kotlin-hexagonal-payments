package com.example.ms_kotlin_hexagonal_payments.domain.model

import java.math.BigDecimal
import java.math.RoundingMode

data class Money(val amount: BigDecimal, val currency: String = "BRL") {
    init {
        require(currency.isNotBlank()) { "currency must not be blank" }
        require(amount.scale() <= 2) { "amount must have at most 2 decimal places" }
        require(amount >= BigDecimal.ZERO) { "amount must be >= 0" }
    }

    fun normalized(): Money =
        copy(amount = amount.setScale(2, RoundingMode.HALF_UP))
}
