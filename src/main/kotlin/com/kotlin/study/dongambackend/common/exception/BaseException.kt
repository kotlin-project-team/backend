package com.kotlin.study.dongambackend.common.exception

import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import lombok.AllArgsConstructor
import lombok.Getter

@Getter
@AllArgsConstructor
class BaseException(status: ResponseStatusType) : Exception() {
    val status: ResponseStatusType = ResponseStatusType.INTERNAL_SERVER_ERROR
}
