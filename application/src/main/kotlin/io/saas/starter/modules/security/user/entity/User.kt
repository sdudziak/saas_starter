package io.saas.starter.modules.security.user.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table
import io.saas.starter.modules.security.signin.dto.User as UserDto

@Entity
@Table(name = "`user`")
class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    val id: Long? = null,

    @Column(name = "email", nullable = false, unique = true, updatable = false)
    val email: String? = null,

    @Column(name = "password", nullable = false)
    val password: String? = null,

    @Column(name = "verified", nullable = false)
    val verified: Boolean? = false,

    @Column(name = "activated", nullable = false)
    val activated: Boolean? = false,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Collection<Role> = emptyList()

) {

    companion object {
        @JvmStatic
        fun createFromUserDto(userDto: UserDto, encodedPassword: String, defaultRole: Role): User = User(
            null,
            userDto.email,
            encodedPassword,
            verified = false,
            activated = false,
            roles = listOf(defaultRole)
        )
    }

    override fun toString() = "User[id='%s', email='%s', password='%s', roles=%s]"
        .format(id, email, password, roles.toString())
}
