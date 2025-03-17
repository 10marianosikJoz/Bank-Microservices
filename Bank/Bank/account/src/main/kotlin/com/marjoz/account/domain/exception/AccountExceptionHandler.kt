package com.marjoz.account.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
internal class AccountExceptionHandler {

    @ExceptionHandler(AccountDomainException::class)
    fun handleException(accountDomainException: AccountDomainException) : ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(HttpStatus.BAD_REQUEST.reasonPhrase,
                                            accountDomainException.message.toString()),
                              HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(methodArgNotValidException: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = mutableMapOf<String, String>()
        methodArgNotValidException.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage ?: "Invalid value"
            errors[fieldName] = errorMessage
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    internal data class ErrorResponse(val responseCode: String, val message : String)
}