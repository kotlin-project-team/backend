package com.kotlin.study.dongambackend.common.exception.common

import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.common.type.ResponseStatusType

class NotFoundException(status: ResponseStatusType) : BaseException(status)