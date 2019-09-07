package io.saas.starter.modules.security.user

import io.saas.starter.modules.application.security.user.MyUserPrincipal
import io.saas.starter.modules.security.user.entity.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    @Autowired private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByEmail(username)
        user ?: throw UsernameNotFoundException(username)

        return MyUserPrincipal(user)
    }
}
