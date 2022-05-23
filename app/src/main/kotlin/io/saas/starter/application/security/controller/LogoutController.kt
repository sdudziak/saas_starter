package io.saas.starter.application.security.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.core.oidc.OidcIdToken
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import javax.servlet.http.HttpServletRequest

@Controller
class LogoutController(
    clientRegistrationRepository: ClientRegistrationRepository,
    @Value("client-registration-id") clientRegistrationId: String
) {
    private val registration: ClientRegistration = clientRegistrationRepository
        .findByRegistrationId(clientRegistrationId)

    @PostMapping("/api/logout")
    fun logout(
        request: HttpServletRequest,
        @AuthenticationPrincipal(expression = "idToken") idToken: OidcIdToken
    ): ResponseEntity<*> {
        val logoutUrl = this.registration.providerDetails.configurationMetadata["end_session_endpoint"]
        request.session.invalidate()
        return ResponseEntity.ok().body(
            mapOf(
                "logoutUrl" to logoutUrl.toString(),
                "idToken" to idToken.tokenValue
            )
        )
    }
}
