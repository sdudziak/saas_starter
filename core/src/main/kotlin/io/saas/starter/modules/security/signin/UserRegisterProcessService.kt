package io.saas.starter.modules.security.signin

import io.saas.starter.modules.security.signin.dto.User

interface UserRegisterProcessService {

    fun registerNewUserAccount(userDto: User)
}
