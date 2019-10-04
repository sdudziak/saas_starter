package io.saas.starter.modules.security.user.entity.repository

import org.springframework.data.jpa.repository.JpaRepository
import io.saas.starter.modules.security.user.entity.Privilege

interface PrivilegeRepository : JpaRepository<Privilege, Int> {
    fun findByName(name: String): Privilege?
}
