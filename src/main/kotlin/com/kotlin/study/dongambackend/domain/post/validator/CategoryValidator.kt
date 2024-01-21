package com.kotlin.study.dongambackend.domain.post.validator

import com.kotlin.study.dongambackend.domain.user.validator.ValidateUserRole
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CategoryValidator : ConstraintValidator<ValidateUserRole, String> {

    lateinit var validateCategory: ValidateUserRole

    override fun initialize(constraintAnnotation: ValidateUserRole) {
        this.validateCategory = constraintAnnotation
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        val enumValues = this.validateCategory.enumClass.java.enumConstants
        if (enumValues != null) {
            for (enumValue in enumValues) {
                if (value.equals(enumValue.toString())
                    || this.validateCategory.ignoreCase && value.equals(enumValue.toString())
                ) {
                    return true
                }
            }
        }

        return false
    }
}