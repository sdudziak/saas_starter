package io.saas.starter.modules.security.user.entity.repository

import org.springframework.data.jpa.repository.JpaRepository
import io.saas.starter.modules.security.user.entity.Role

interface RoleRepository : JpaRepository<Role, Int> {
    fun findByName(name: String): Role?
}
