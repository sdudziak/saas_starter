package io.saas.starter.modules.security.user.entity

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class PrivilegeTest {
    companion object {
        val exampleId = 1.toLong()
        val exampleName = "none"
        val exampleEmptyRoles = listOf<Role>()
        val exampleRoles = listOf(Role(exampleId, exampleName))
        val examplePrivileges = listOf(
            Privilege(exampleId, exampleName, exampleEmptyRoles) to "Privilege[name='none']",
            Privilege(exampleId, exampleName, exampleRoles) to "Privilege[name='none']"
        )
    }

    @TestFactory
    fun `privilege are created properly`() = examplePrivileges
        .map { (input, expected) ->
            DynamicTest.dynamicTest("Privilege $input is equal to expected $expected") {
                Assertions.assertEquals(expected, input.toString())
                Assertions.assertEquals(exampleId, input.id)
                Assertions.assertEquals(exampleName, input.name)
                Assertions.assertNotNull(input.roles)
                input.roles = exampleRoles
                Assertions.assertEquals(exampleRoles, input.roles)
            }
        }
}
