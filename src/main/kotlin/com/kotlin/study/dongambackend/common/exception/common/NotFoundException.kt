package com.kotlin.study.dongambackend.common.exception.common

import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.common.type.ResponseStatusType

class NotFoundException : BaseException(ResponseStatusType.NOT_FOUND)