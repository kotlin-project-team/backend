package com.kotlin.study.dongambackend.common.annotation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class TypeValidator : ConstraintValidator<ValidateCategory, String> {

    lateinit var validateCategory: ValidateCategory

    override fun initialize(constraintAnnotation: ValidateCategory) {
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