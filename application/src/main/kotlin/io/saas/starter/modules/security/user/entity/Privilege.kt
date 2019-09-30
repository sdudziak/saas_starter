package io.saas.starter.modules.security.user.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
data class Privilege(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(unique = true)
    val name: String? = null,

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
    var roles: Collection<Role> = emptyList()
) {
    override fun toString() = "Privilege[name='%s']".format(name)
}
