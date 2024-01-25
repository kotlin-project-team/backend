package com.kotlin.study.dongambackend.domain.user.validator

import com.kotlin.study.dongambackend.domain.post.validator.UserRoleValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [UserRoleValidator::class])
annotation class ValidateUserRole(
    val enumClass: KClass<out Enum<*>>,
    val message: String = "올바른 유저 권한을 입력해주세요.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val ignoreCase: Boolean = false
)