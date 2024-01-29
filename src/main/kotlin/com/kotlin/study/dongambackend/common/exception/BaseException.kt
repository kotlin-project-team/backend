package com.kotlin.study.dongambackend.common.exception

import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import lombok.AllArgsConstructor
import lombok.Getter

@Getter
@AllArgsConstructor
class BaseException(val status: ResponseStatusType) : Exception()
