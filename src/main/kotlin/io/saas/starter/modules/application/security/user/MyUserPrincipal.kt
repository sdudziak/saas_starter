package io.saas.starter.modules.application.security.user

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import io.saas.starter.modules.security.user.entity.User

class MyUserPrincipal(val user: User) : UserDetails {
    override fun getAuthorities() = user.roles
                                                                    .map { SimpleGrantedAuthority(it.name) }
                                                                    .toMutableList()

    override fun isEnabled() = user.activated!!
    override fun getUsername() = user.email!!
    override fun isCredentialsNonExpired() = user.verified!!
    override fun getPassword() = user.password!!
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
}
