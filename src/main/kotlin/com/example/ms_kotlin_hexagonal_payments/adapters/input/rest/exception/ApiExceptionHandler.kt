package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.exception

import com.example.ms_kotlin_hexagonal_payments.domain.exception.BusinessException
import com.example.ms_kotlin_hexagonal_payments.domain.exception.BusinessRuleException
import com.example.ms_kotlin_hexagonal_payments.domain.exception.NotFoundException
import com.example.ms_kotlin_hexagonal_payments.domain.exception.PaymentNotApprovedForRefundException
import com.example.ms_kotlin_hexagonal_payments.domain.exception.RefundAmountGreaterThanPaymentException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.json.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import tools.jackson.databind.exc.InvalidFormatException
import java.net.URI
import java.time.Instant
import java.util.UUID

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(
        ex: NotFoundException,
        req: HttpServletRequest
    ): ResponseEntity<ProblemDetail> {
        val problem = buildProblemDetail(
            status = HttpStatus.NOT_FOUND,
            title = "Resource Not Found",
            detail = ex.message ?: "Recurso não encontrado",
            path = req.requestURI,
            type = "https://api.ms-payments/errors/not-found"
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem)
    }

    @ExceptionHandler(PaymentNotApprovedForRefundException::class)
    fun handlePaymentNotApprovedForRefund(
        ex: PaymentNotApprovedForRefundException,
        req: HttpServletRequest
    ): ResponseEntity<ProblemDetail> {
        val problem = buildProblemDetail(
            status = HttpStatus.UNPROCESSABLE_ENTITY,
            title = "Refund Not Allowed",
            detail = ex.message ?: "Apenas pagamentos com status APPROVED podem ser estornados",
            path = req.requestURI,
            type = "https://api.ms-payments/errors/payment-not-approved-for-refund"
        )
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(problem)
    }

    @ExceptionHandler(RefundAmountGreaterThanPaymentException::class)
    fun handleRefundAmountGreaterThanPayment(
        ex: RefundAmountGreaterThanPaymentException,
        req: HttpServletRequest
    ): ResponseEntity<ProblemDetail> {
        val problem = buildProblemDetail(
            status = HttpStatus.UNPROCESSABLE_ENTITY,
            title = "Invalid Refund Amount",
            detail = ex.message ?: "O valor do estorno não pode ser maior que o valor do pagamento",
            path = req.requestURI,
            type = "https://api.ms-payments/errors/refund-amount-greater-than-payment"
        )
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(problem)
    }

    @ExceptionHandler(BusinessRuleException::class)
    fun handleBusinessRule(
        ex: BusinessRuleException,
        req: HttpServletRequest
    ): ResponseEntity<ProblemDetail> {
        val problem = buildProblemDetail(
            status = HttpStatus.UNPROCESSABLE_ENTITY,
            title = "Business Rule Violation",
            detail = ex.message ?: "Regra de negócio violada",
            path = req.requestURI,
            type = "https://api.ms-payments/errors/business-rule-violation"
        )
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(problem)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusiness(
        ex: BusinessException,
        req: HttpServletRequest
    ): ResponseEntity<ProblemDetail> {
        val problem = buildProblemDetail(
            status = HttpStatus.UNPROCESSABLE_ENTITY,
            title = "Business Error",
            detail = ex.message ?: "Erro de negócio",
            path = req.requestURI,
            type = "https://api.ms-payments/errors/business-error"
        )
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(problem)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(
        ex: MethodArgumentNotValidException,
        req: HttpServletRequest
    ): ResponseEntity<ProblemDetail> {
        val errors = ex.bindingResult.allErrors
            .filterIsInstance<FieldError>()
            .associate { fieldError ->
                fieldError.field to (fieldError.defaultMessage ?: "invalid")
            }

        val problem = buildProblemDetail(
            status = HttpStatus.BAD_REQUEST,
            title = "Validation Failed",
            detail = "Um ou mais campos estão inválidos",
            path = req.requestURI,
            type = "https://api.ms-payments/errors/validation-failed"
        )

        problem.setProperty("fieldErrors", errors)

        return ResponseEntity.badRequest().body(problem)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatch(
        ex: MethodArgumentTypeMismatchException,
        req: HttpServletRequest
    ): ResponseEntity<ProblemDetail> {
        val detail = when {
            ex.requiredType == UUID::class.java -> "Invalid UUID value: ${ex.value}"
            else -> "Invalid value for parameter '${ex.name}': ${ex.value}"
        }

        val problem = buildProblemDetail(
            status = HttpStatus.BAD_REQUEST,
            title = "Bad Request",
            detail = detail,
            path = req.requestURI,
            type = "https://api.ms-payments/errors/bad-request"
        )

        return ResponseEntity.badRequest().body(problem)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingParameter(
        ex: MissingServletRequestParameterException,
        req: HttpServletRequest
    ): ResponseEntity<ProblemDetail> {
        val problem = buildProblemDetail(
            status = HttpStatus.BAD_REQUEST,
            title = "Bad Request",
            detail = "Missing required parameter: ${ex.parameterName}",
            path = req.requestURI,
            type = "https://api.ms-payments/errors/bad-request"
        )

        return ResponseEntity.badRequest().body(problem)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        req: HttpServletRequest
    ): ResponseEntity<ProblemDetail> {
        val rootCause = ex.mostSpecificCause

        val detail = when (rootCause) {
            is JsonParseException -> "Malformed JSON request"
            is InvalidFormatException -> "Invalid value format in request body"
            else -> "Request body is missing or invalid"
        }

        val problem = buildProblemDetail(
            status = HttpStatus.BAD_REQUEST,
            title = "Bad Request",
            detail = detail,
            path = req.requestURI,
            type = "https://api.ms-payments/errors/bad-request"
        )

        return ResponseEntity.badRequest().body(problem)
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneric(
        ex: Exception,
        req: HttpServletRequest
    ): ResponseEntity<ProblemDetail> {
        val problem = buildProblemDetail(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            title = "Internal Server Error",
            detail = "An unexpected internal error occurred",
            path = req.requestURI,
            type = "https://api.ms-payments/errors/internal-server-error"
        )

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem)
    }

    private fun buildProblemDetail(
        status: HttpStatus,
        title: String,
        detail: String,
        path: String,
        type: String
    ): ProblemDetail {
        val problem = ProblemDetail.forStatusAndDetail(status, detail)
        problem.title = title
        problem.type = URI.create(type)
        problem.instance = URI.create(path)

        problem.setProperty("timestamp", Instant.now().toString())
        problem.setProperty("path", path)

        return problem
    }
}