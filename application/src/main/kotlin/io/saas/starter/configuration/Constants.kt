package io.saas.starter.configuration

object Constants {
    object Route {
        const val INDEX = "/"
        const val LOGIN = "/login"
        const val LOGOUT = "/logout"
        const val REGISTER = "/register"
    }
    object Security {
        const val LOGOUT = Route.LOGOUT
        const val INDEX = Route.INDEX
        const val LOGIN = "${Route.LOGIN}*"
        const val REGISTER = "${Route.REGISTER}*"

        object Roles {
            const val ADMIN = "ROLE_ADMIN"
            const val USER = "ROLE_USER"
            const val UNVERIFIED_USER = "ROLE_UNVERIFIED_USER"
        }
    }
}
