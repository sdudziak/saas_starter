package io.saas.starter.modules.security.user.entity

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class RoleTest {
    companion object {

        private const val exampleId = 1.toLong()
        private const val exampleName = "none"

        private val exampleEmptyUsers = emptySet<User>()

        val exampleUsers = listOf(User(exampleId, "lol", "wtf"))
        val examplePrivileges = listOf(Privilege(exampleId, "none"))
        val exampleRoles = listOf(
            Role(exampleId, exampleName, exampleUsers, examplePrivileges)
                to "Role[name='none', privileges=[Privilege[name='none']]]",
            Role(exampleId, exampleName, exampleEmptyUsers) to "Role[name='none', privileges=[]]",
            Role(exampleId, exampleName, privileges = examplePrivileges)
                to "Role[name='none', privileges=[Privilege[name='none']]]",
            Role(exampleId, exampleName) to "Role[name='none', privileges=[]]"
        )
    }

    @TestFactory
    fun `role is created as expected`() = exampleRoles
        .map { (input, expectedToString) ->
            DynamicTest
                .dynamicTest("Role toString produces: $input, which is same as expected: $expectedToString") {
                    Assertions.assertEquals(expectedToString, input.toString())
                }
        }

    @Test
    fun `Getters and setters working correctly`() {
        val role = Role(exampleId, exampleName, exampleUsers)
        role.privileges = examplePrivileges
        Assertions.assertEquals(examplePrivileges, role.privileges)
        Assertions.assertEquals(exampleId, role.id)
        Assertions.assertEquals(exampleName, role.name)
        Assertions.assertEquals(exampleUsers, role.users)
    }
}
