package com.example.practice.exception

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(e: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.builder(e, HttpStatus.NOT_FOUND, e.message ?: "Entity not found").build()
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse.builder(e, HttpStatus.BAD_REQUEST, "'${e.value}' is an invalid value.").build()
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}