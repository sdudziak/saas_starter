package io.saas.starter.modules.security.signin.exceptions

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class EmailIsAlreadyInUseExceptionTest {
    companion object {
        const val EXAMPLE_EMAIL = "john.doe@example.com"
    }

    @Test
    fun `email address is visible in message`() {
        val exception = EmailIsAlreadyInUseException(EXAMPLE_EMAIL)
        Assertions.assertTrue(exception.message!!.contains(EXAMPLE_EMAIL))
    }
}
