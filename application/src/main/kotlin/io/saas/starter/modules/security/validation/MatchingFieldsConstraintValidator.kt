package io.saas.starter.modules.security.validation

import org.apache.commons.beanutils.BeanUtils
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class MatchingFieldsConstraintValidator : ConstraintValidator<ValidMatchingFields, Any> {

    private companion object {
        const val checkedFieldsCount = 2
    }

    private lateinit var checkedFileds: Array<String>

    override fun initialize(constraintAnnotation: ValidMatchingFields?) {
        checkedFileds = arrayOf(constraintAnnotation!!.referenceFiled, constraintAnnotation.equalFiledName)
    }

    override fun isValid(reference: Any?, context: ConstraintValidatorContext?): Boolean {
        val checkedFields = reference!!::class
            .java
            .declaredFields
            .filter { checkedFileds.contains(it.name) }

        val referencedValue = BeanUtils.getProperty(reference, checkedFields[0].name)

        return checkedFields
            .map { BeanUtils.getProperty(reference, it.name) }
            .all { it.equals(referencedValue) }
    }
}
