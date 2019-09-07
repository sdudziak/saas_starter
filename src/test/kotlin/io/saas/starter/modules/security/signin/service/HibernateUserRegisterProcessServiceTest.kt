package io.saas.starter.modules.security.signin.service

import io.mockk.every
import io.mockk.mockk
import io.saas.starter.configuration.Constants.Security.Roles
import io.saas.starter.modules.security.signin.exceptions.EmailIsAlreadyInUseException
import io.saas.starter.modules.security.signin.exceptions.UnableToFindTheRoleException
import io.saas.starter.modules.security.user.entity.Role
import io.saas.starter.modules.security.user.entity.User
import io.saas.starter.modules.security.user.entity.repository.RoleRepository
import io.saas.starter.modules.security.user.entity.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import io.saas.starter.modules.security.signin.dto.User as UserDto

internal class HibernateUserRegisterProcessServiceTest {
    companion object {
        private const val EXAMPLE_EMAIL = "email@is.ok"
        private const val EXAMPLE_PASSWORD = "abc"
        val EXAMPLE_DEFAULT_ROLE = Role(1, Roles.UNVERIFIED_USER)
        val EXAMPLE_USER_DTO = UserDto(EXAMPLE_EMAIL, EXAMPLE_PASSWORD, EXAMPLE_PASSWORD)
        val EXAMPLE_EXISTING_USER = User(null, EXAMPLE_EMAIL, EXAMPLE_PASSWORD, roles = listOf(EXAMPLE_DEFAULT_ROLE))
    }

    @Test
    fun `if email is already taken service will complain about it`() {
        val userRegisterProcessService = HibernateUserRegisterProcessService(
            createUserRepositoryWhichReturn(EXAMPLE_EXISTING_USER),
            createRoleRepositoryWhichReturn(null),
            BCryptPasswordEncoder()
        )
        Assertions.assertThrows(EmailIsAlreadyInUseException::class.java) {
            userRegisterProcessService.registerNewUserAccount(EXAMPLE_USER_DTO)
        }
    }

    @Test
    fun `if there is no Role in the repository complain about it`() {
        val userRegisterProcessService = HibernateUserRegisterProcessService(
            createUserRepositoryWhichReturn(null),
            createRoleRepositoryWhichReturn(null),
            BCryptPasswordEncoder()
        )
        Assertions.assertThrows(UnableToFindTheRoleException::class.java) {
            userRegisterProcessService.registerNewUserAccount(EXAMPLE_USER_DTO)
        }
    }

    @Test
    fun `if user is not registered and the default role exists, user is created and stored in repository`() {

        val passwordEncoder = mockk<BCryptPasswordEncoder>()
        every { passwordEncoder.encode(any()) } answers { EXAMPLE_PASSWORD }

        val userRegisterProcessService = HibernateUserRegisterProcessService(
            createSavingRepository(),
            createRoleRepositoryWhichReturn(EXAMPLE_DEFAULT_ROLE),
            passwordEncoder
        )

        Assertions.assertDoesNotThrow {
            userRegisterProcessService.registerNewUserAccount(EXAMPLE_USER_DTO)
        }
    }
    private fun createSavingRepository(): UserRepository {
        val userRepositoryMock = mockk<UserRepository>(relaxed = true)

        every { userRepositoryMock.findByEmail(any()) } answers { null }
        every { userRepositoryMock.save(any()) } answers { Unit }

        return userRepositoryMock
    }

    private fun createRoleRepositoryWhichReturn(role: Role?): RoleRepository {
        val repositoryMock = mockk<RoleRepository>()
        every {
            repositoryMock.findByName(any())
        } answers { role }

        return repositoryMock
    }

    private fun createUserRepositoryWhichReturn(user: User?): UserRepository {
        val repositoryMock = mockk<UserRepository>()
        every {
            repositoryMock.findByEmail(any())
        } answers {
            user
        }

        return repositoryMock
    }
}
