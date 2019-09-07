package io.saas.starter.modules.security.validation

import org.passay.CharacterRule
import org.passay.EnglishCharacterData
import org.passay.LengthRule
import org.passay.PasswordData
import org.passay.PasswordValidator
import org.passay.WhitespaceRule
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class PasswordConstraintValidator : ConstraintValidator<ValidPassword, String> {

    companion object {
        const val passwordMinimalLength = 8
        const val passwordMaximumLength = 255
        const val minimalSpecialCharacterRequirementsAmount = 1
    }

    override fun isValid(password: String?, context: ConstraintValidatorContext?): Boolean {
        val validator = PasswordValidator(
            listOf(
                LengthRule(Companion.passwordMinimalLength, Companion.passwordMaximumLength),
                CharacterRule(EnglishCharacterData.UpperCase, Companion.minimalSpecialCharacterRequirementsAmount),
                CharacterRule(EnglishCharacterData.Digit, Companion.minimalSpecialCharacterRequirementsAmount),
                WhitespaceRule()
            )
        )

        val result = validator.validate(PasswordData(password))
        if (result.isValid) {
            return true
        }

        context!!.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(validator.getMessages(result).joinToString { it })
                .addConstraintViolation()

        return false
    }
}
