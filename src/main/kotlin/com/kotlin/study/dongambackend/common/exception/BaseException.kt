package com.kotlin.study.dongambackend.common.exception

import com.kotlin.study.dongambackend.common.type.ResponseStatusType
import lombok.AllArgsConstructor
import lombok.Getter

@Getter
@AllArgsConstructor
// 해당 클래스를 상속받기 위해서는 open 키워드를 추가해야한다.
open class BaseException(val status: ResponseStatusType) : Exception()
