package com.example.ms_kotlin_hexagonal_payments.domain.exception


open class DomainException(message: String) : RuntimeException(message)

class NotFoundException(message: String) : DomainException(message)

class BusinessRuleException(message: String) : DomainException(message)

class BusinessException(message: String) : RuntimeException(message)