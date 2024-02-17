package com.kotlin.study.dongambackend.common.exception

import com.kotlin.study.dongambackend.common.dto.BaseResponse
import com.kotlin.study.dongambackend.common.type.ResponseStatusType

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * developer custom exception
     */
    @ExceptionHandler(BaseException::class)
    fun handleBaseException(exception: BaseException): ResponseEntity<BaseResponse<Unit>>
        = BaseResponse<Unit>(exception.status, true).convert()

    /**
     * request valid exception
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleRequestValidException(exception: MethodArgumentNotValidException): ResponseEntity<BaseResponse<Unit>>
        = BaseResponse<Unit>(ResponseStatusType.BAD_REQUEST, true).convert()

    /**
     * enum validate exception
     */
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exception: IllegalArgumentException)
        = BaseResponse<Unit>(ResponseStatusType.BAD_REQUEST, true).convert()
}