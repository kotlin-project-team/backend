package com.kotlin.study.dongambackend.common.config

import lombok.AllArgsConstructor
import lombok.Getter

@Getter
@AllArgsConstructor
class BaseException(status: ResponseStatus) : Exception() {
    val status: ResponseStatus = ResponseStatus.INTERNAL_SERVER_ERROR
}
