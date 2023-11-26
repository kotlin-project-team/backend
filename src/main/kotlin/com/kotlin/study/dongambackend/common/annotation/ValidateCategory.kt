package com.kotlin.study.dongambackend.common.annotation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [TypeValidator::class])
annotation class ValidateCategory(
    val enumClass: KClass<out Enum<*>>,
    val message: String = "올바른 카테고리 종류를 입력해주세요.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val ignoreCase: Boolean = false
)