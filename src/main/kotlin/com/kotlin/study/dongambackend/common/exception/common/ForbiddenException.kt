package com.kotlin.study.dongambackend.common.exception.common;

import com.kotlin.study.dongambackend.common.type.ResponseStatusType;
import com.kotlin.study.dongambackend.common.exception.BaseException;

class ForbiddenException(status: ResponseStatusType) : BaseException(status)