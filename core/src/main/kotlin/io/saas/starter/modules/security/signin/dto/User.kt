package io.saas.starter.modules.security.signin.dto

import io.saas.starter.modules.security.validation.ValidMatchingFields
import io.saas.starter.modules.security.validation.ValidPassword
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@ValidMatchingFields(
    referenceFiled = "password",
    equalFiledName = "matchingPassword",
    message = "{validation.invalid.matching_password_different}"
)
data class User constructor(
    @get: NotBlank(message = "{validation.invalid.empty_email}")
    @get: Email(message = "{validation.invalid.email}")
    val email: String = "",

    @get: ValidPassword(message = "{validation.invalid.password}")
    val password: String = "",

    val matchingPassword: String = ""
)
