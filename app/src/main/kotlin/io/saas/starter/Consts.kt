package io.saas.starter

object Consts {
    object Route {
        const val INDEX = "/"
        const val LOGIN = "/login"
        const val LOGOUT = "/logout"
        const val REGISTER = "/register"
        const val ASSETS_PATH = "/{path:[^\\.]*}"

        object Redirect {
            const val ASSETS = "forward:/"
        }
    }

    object Security {

        object Roles {
            const val ADMIN = "ROLE_ADMIN"
            const val USER = "ROLE_USER"
            const val UNVERIFIED_USER = "ROLE_UNVERIFIED_USER"
        }
    }
}
