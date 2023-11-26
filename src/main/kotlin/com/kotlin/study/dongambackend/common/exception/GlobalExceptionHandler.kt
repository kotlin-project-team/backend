package com.kotlin.study.dongambackend.common.exception

import com.kotlin.study.dongambackend.common.dto.BaseResponse
import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// GlobalExceptionHandler - @RestControllerAdvice
@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(value = [BaseException::class])
    fun handleBaseException(ex: BaseException): ResponseEntity<BaseResponse<Unit>> {
        val responseStatus: ResponseStatusType = ex.status
        val baseResponse = BaseResponse<Unit>(responseStatus, true) // 실패 상태로 BaseResponse 생성
        return baseResponse.convert()
    }
}