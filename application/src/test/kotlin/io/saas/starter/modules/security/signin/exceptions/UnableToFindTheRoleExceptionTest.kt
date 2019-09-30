package io.saas.starter.modules.security.signin.exceptions

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import io.saas.starter.configuration.Constants

internal class UnableToFindTheRoleExceptionTest {
    companion object {
        const val EXAMPLE_ROLE = Constants.Security.Roles.UNVERIFIED_USER
    }

    @Test
    fun `email address is visible in message`() {
        val exception = UnableToFindTheRoleException(EXAMPLE_ROLE)
        Assertions.assertTrue(exception.message!!.contains(EXAMPLE_ROLE))
    }
}
