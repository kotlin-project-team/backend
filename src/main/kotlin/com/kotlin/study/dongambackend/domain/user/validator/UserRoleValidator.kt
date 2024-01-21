package com.kotlin.study.dongambackend.domain.post.validator

import com.kotlin.study.dongambackend.domain.user.validator.ValidateUserRole
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UserRoleValidator : ConstraintValidator<ValidateUserRole, String> {

    lateinit var validateRole: ValidateUserRole

    override fun initialize(constraintAnnotation: ValidateUserRole) {
        this.validateRole = constraintAnnotation
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        val enumValues = this.validateRole.enumClass.java.enumConstants
        if (enumValues != null) {
            for (enumValue in enumValues) {
                if (value.equals(enumValue.toString())
                    || this.validateRole.ignoreCase && value.equals(enumValue.toString())
                ) {
                    return true
                }
            }
        }

        return false
    }
}