package com.tamastudy.restapi.errors

import com.tamastudy.restapi.controllers.PostController
import com.tamastudy.restapi.dto.ErrorDetail
import com.tamastudy.restapi.dto.ErrorDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

//@RestControllerAdvice(basePackageClasses = [PostController::class])
@RestControllerAdvice
class GlobalAdvice {
    // Exception :
    @ExceptionHandler(Exception::class)
    fun defaultException(request: HttpServletRequest?, e: Exception): ResponseEntity<ErrorDto> {
        val errors = mutableListOf<ErrorDetail>()
        ErrorDetail().apply {
            this.message = e.message
        }.let {
            errors.add(it)
        }
        val errorResponse = ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, errors)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validException(request: HttpServletRequest?, e: MethodArgumentNotValidException): ResponseEntity<ErrorDto> {
        val errors = mutableListOf<ErrorDetail>()
        e.bindingResult.allErrors.forEach { errorObject ->
            ErrorDetail().apply {
                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue
            }.let {
                errors.add(it)
            }
        }

        val errorResponse = ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, errors)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}