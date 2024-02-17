package com.kotlin.study.dongambackend.security.exception

import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.common.type.ResponseStatusType

class TokenNotFoundException : BaseException(ResponseStatusType.UNAUTHORIZED)