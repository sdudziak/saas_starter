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

@Entity
data class Role(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    val id: Long? = null,

    @Column(unique = true)
    val name: String = "",

    @ManyToMany(mappedBy = "roles")
    val users: Collection<User> = emptyList(),

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "roles_privileges",
        joinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "privilege_id", referencedColumnName = "id")]
    )
    var privileges: Collection<Privilege> = emptyList()
) {
    override fun toString() = "Role[name='%s', privileges=%s]".format(name, privileges.toString())
}
