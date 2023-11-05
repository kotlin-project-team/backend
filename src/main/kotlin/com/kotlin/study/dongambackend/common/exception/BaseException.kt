package com.kotlin.study.dongambackend.common.exception

import com.kotlin.study.dongambackend.common.type.ResponseStatus
import lombok.AllArgsConstructor
import lombok.Getter

@Getter
@AllArgsConstructor
class BaseException(status: ResponseStatus) : Exception() {
    val status: ResponseStatus = ResponseStatus.INTERNAL_SERVER_ERROR
}
