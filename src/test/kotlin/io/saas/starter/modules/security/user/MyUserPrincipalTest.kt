package io.saas.starter.modules.security.user

import io.saas.starter.modules.application.security.user.MyUserPrincipal
import io.saas.starter.modules.security.user.entity.Role
import io.saas.starter.modules.security.user.entity.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.security.core.authority.SimpleGrantedAuthority

internal class MyUserPrincipalTest {
    companion object {
        private val exampleRolesList = listOf(Role(1.toLong(), "none"))
        val exampleListOfUsers = listOf(
        User(1, "user", "pass", roles = exampleRolesList),
        User(2, "user2", "pass2", roles = exampleRolesList),
        User(3, "user3", "pass3", roles = exampleRolesList)
        )
    }

    @TestFactory
    fun `myUserPrincipal is providing right data`() = exampleListOfUsers
        .map { Pair(it, MyUserPrincipal(it)) }
        .map {
            val (user, principal) = it
            DynamicTest.dynamicTest("User ${user.email} have right authorization data") {
                Assertions.assertEquals(user, principal.user)
                Assertions.assertEquals(user.email, principal.username)
                Assertions.assertEquals(user.password, principal.password)
                Assertions.assertEquals(listOf(SimpleGrantedAuthority("none")), principal.authorities)
                Assertions.assertTrue(listOf(
                    principal.isEnabled,
                    principal.isCredentialsNonExpired,
                    principal.isAccountNonExpired,
                    principal.isAccountNonLocked
                ).all { true })
            }
        }
}
