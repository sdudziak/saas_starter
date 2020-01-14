package io.saas.starter.modules.security.user

import io.mockk.every
import io.mockk.mockk
import io.saas.starter.modules.security.user.entity.User
import io.saas.starter.modules.security.user.entity.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.core.userdetails.UsernameNotFoundException

internal class CustomUserDetailsServiceTest {

    @Test
    fun `loadUserByUsername will throw if User won't be found in repository`() {
        val repository = getEmptyRepository()
        val service = CustomUserDetailsService(repository)
        Assertions.assertThrows(UsernameNotFoundException::class.java) {
            service.loadUserByUsername("doesn't matter")
        }
    }

    @Test
    fun `loadUserByUsername will return user entity when repository returns user`() {
        val repository = getRepositoryWhichAlwaysReturnDummyUser()
        val service = CustomUserDetailsService(repository)
        val dummyUser = service.loadUserByUsername("anything")
        Assertions.assertEquals("dummy", dummyUser.username)
    }

    private fun getEmptyRepository(): UserRepository {
        val repository = mockk<UserRepository>()
        every {
            repository.findByEmail(any())
        } answers { null }

        return repository
    }

    private fun getRepositoryWhichAlwaysReturnDummyUser(): UserRepository {
        val repository = mockk<UserRepository>()
        every {
            repository.findByEmail(any())
        } answers { User(1, "dummy", "user") }

        return repository
    }
}
