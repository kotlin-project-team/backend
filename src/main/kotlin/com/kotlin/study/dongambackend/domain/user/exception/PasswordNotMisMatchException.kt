package com.kotlin.study.dongambackend.domain.user.exception

import com.kotlin.study.dongambackend.common.exception.BaseException
import com.kotlin.study.dongambackend.common.type.ResponseStatusType

class PasswordNotMisMatchException(statusType: ResponseStatusType) : BaseException(statusType)