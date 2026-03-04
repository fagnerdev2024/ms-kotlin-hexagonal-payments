package com.example.ms_kotlin_hexagonal_payments.adapters.input.rest.exception


import com.example.ms_kotlin_hexagonal_payments.domain.exception.BusinessRuleException
import com.example.ms_kotlin_hexagonal_payments.domain.exception.NotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.Instant

@RestControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException, req: HttpServletRequest): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem(HttpStatus.NOT_FOUND, ex.message, req.requestURI))

    @ExceptionHandler(BusinessRuleException::class)
    fun handleBusiness(ex: BusinessRuleException, req: HttpServletRequest): ResponseEntity<Any> =
        ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(problem(HttpStatus.UNPROCESSABLE_ENTITY, ex.message, req.requestURI))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException, req: HttpServletRequest): ResponseEntity<Any> {
        val errors = ex.bindingResult.allErrors
            .filterIsInstance<FieldError>()
            .associate { it.field to (it.defaultMessage ?: "invalid") }

        val body = mutableMapOf<String, Any>(
            "timestamp" to Instant.now().toString(),
            "status" to 400,
            "error" to "Bad Request",
            "path" to req.requestURI,
            "message" to "Validation failed",
            "fieldErrors" to errors
        )
        return ResponseEntity.badRequest().body(body)
    }

    private fun problem(status: HttpStatus, message: String?, path: String): Map<String, Any> =
        mapOf(
            "timestamp" to Instant.now().toString(),
            "status" to status.value(),
            "error" to status.reasonPhrase,
            "message" to (message ?: "Unexpected error"),
            "path" to path
        )
}