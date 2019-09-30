package io.saas.starter.modules.security.user.entity.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import io.saas.starter.modules.security.user.entity.User

@Repository
interface UserRepository : JpaRepository<User, String> {
    fun findByEmail(email: String?): User?
    fun save(user: User): Unit
}
