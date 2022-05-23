package io.saas.starter.application.security.configuration

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter
import org.springframework.security.web.util.matcher.RequestMatcher

@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        // @formatter:off
        http
            .authorizeRequests()
            .antMatchers("/**/*.{js,html,css}").permitAll()
            .antMatchers("/", "/user").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
            .and()
            .oauth2ResourceServer().jwt()

        http.requiresChannel()
            .requestMatchers(RequestMatcher { r -> r.getHeader("X-Forwarded-Proto") != null })
            .requiresSecure()

        http.csrf()
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

        http.headers()
            .contentSecurityPolicy("script-src 'self'; report-to /csp-report-endpoint/")
            .and()
            .referrerPolicy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN)
//            .and()
//            .permissionsPolicy("accelerometer 'none'; camera 'none'; microphone 'none'")
        // @formatter:on
    }
}
