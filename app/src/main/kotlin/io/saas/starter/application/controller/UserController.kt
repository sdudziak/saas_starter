package io.saas.starter.application.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class UserController {
    @GetMapping("/user")
    fun user(@AuthenticationPrincipal user: OidcUser?): OidcUser? = user
}
