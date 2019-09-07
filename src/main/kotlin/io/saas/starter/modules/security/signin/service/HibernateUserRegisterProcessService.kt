package io.saas.starter.modules.security.signin.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import io.saas.starter.configuration.Constants
import io.saas.starter.modules.security.signin.UserRegisterProcessService
import io.saas.starter.modules.security.signin.dto.User
import io.saas.starter.modules.security.signin.exceptions.EmailIsAlreadyInUseException
import io.saas.starter.modules.security.signin.exceptions.UnableToFindTheRoleException
import io.saas.starter.modules.security.user.entity.repository.RoleRepository
import io.saas.starter.modules.security.user.entity.repository.UserRepository
import io.saas.starter.modules.security.user.entity.User as UserEntity

@Service
class HibernateUserRegisterProcessService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) : UserRegisterProcessService {

    override fun registerNewUserAccount(userDto: User) {
        if (isEmailIsAlreadyInUse(userDto.email)) throw EmailIsAlreadyInUseException(userDto.email)
        val unverifiedRole = roleRepository.findByName(Constants.Security.Roles.UNVERIFIED_USER)
            ?: throw UnableToFindTheRoleException(Constants.Security.Roles.UNVERIFIED_USER)
        val user = UserEntity.createFromUserDto(
            userDto,
            passwordEncoder.encode(userDto.password),
            unverifiedRole
        )
        userRepository.save(user)
    }

    private fun isEmailIsAlreadyInUse(email: String): Boolean {
        return userRepository.findByEmail(email) is UserEntity
    }
}
