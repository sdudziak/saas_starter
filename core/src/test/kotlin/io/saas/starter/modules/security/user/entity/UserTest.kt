package io.saas.starter.modules.security.user.entity

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import io.saas.starter.modules.security.signin.dto.User as UserDto

internal class UserTest {

    companion object {

        const val EXAMPLE_EMAIL = "example@email.com"
        const val EXAMPLE_PASSWORD = "S1mpl3_P4s5"

        val EXAMPLE_ROLE_USER = Role(1, "ROLE_USER")

        val exampleListOfUsers = listOf(
            User(1, "user@email.com", "pass", roles = listOf(EXAMPLE_ROLE_USER))
                to "User[id='1', email='user@email.com', password='pass', roles=[Role[name='ROLE_USER', privileges=[]]]]",
            User(2, "user2@email.com", "pass2")
                to "User[id='2', email='user2@email.com', password='pass2', roles=[]]",
            User(3, "user3@email.com", "pass3", roles = emptyList())
                to "User[id='3', email='user3@email.com', password='pass3', roles=[]]"
        )

        val exampleUserIds = listOf<Long>(1, 2, 3, 4)
    }

    @TestFactory
    fun `toString method will map entity as expected`() = exampleListOfUsers
        .map { (input, expected) ->
            DynamicTest.dynamicTest("User $input mapped as $expected") {
                Assertions.assertEquals(expected, input.toString())
            }
        }

    @TestFactory
    fun `user ID is populated correctly`() = exampleUserIds
        .map { Pair(it, User(it, "user", "password")) }
        .map {
            val (id, user) = it
            DynamicTest.dynamicTest("User with id=$id is correctly mapped ${user.id}") {
                Assertions.assertEquals(id, user.id)
            }
        }

    @Test
    fun `user roles are able to set`() {
        val user = User(1, "Abe", "L")
        val roles = listOf(Role(1, "none", emptyList(), listOf(Privilege(1, "none"))))
        user.roles = roles
        Assertions.assertEquals(roles, user.roles)
    }

    @Test
    fun `user can be created from the UserDto object`() {
        val dtoUser = UserDto(EXAMPLE_EMAIL, EXAMPLE_PASSWORD, EXAMPLE_PASSWORD)
        val user = User.createFromUserDto(dtoUser, dtoUser.password, EXAMPLE_ROLE_USER)
        Assertions.assertEquals(
            "User[id='null', email='example@email.com', password='S1mpl3_P4s5'," +
            " roles=[Role[name='ROLE_USER', privileges=[]]]]",
            user.toString()
        )
    }
}
