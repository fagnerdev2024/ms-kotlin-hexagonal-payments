package com.example.ms_kotlin_hexagonal_payments.domain.exception

open class DomainException(message: String) : RuntimeException(message)

open class NotFoundException(message: String) : DomainException(message)

open class BusinessRuleException(message: String) : DomainException(message)

open class BusinessException(message: String) : DomainException(message)

class PaymentNotApprovedForRefundException :
    BusinessRuleException("Apenas pagamentos com status APPROVED podem ser estornados")

class RefundAmountGreaterThanPaymentException :
    BusinessRuleException("O valor do estorno não pode ser maior que o valor do pagamento")