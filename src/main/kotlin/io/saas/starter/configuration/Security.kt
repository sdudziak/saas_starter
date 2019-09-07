package io.saas.starter.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import io.saas.starter.modules.security.user.CustomUserDetailsService

@Configuration
@EnableWebSecurity
class Security(val customUserDetailsService: CustomUserDetailsService) : WebSecurityConfigurerAdapter() {

    companion object {
        const val defaultPasswordEncryptionStrength = 12
        const val defaultTokenValiditySeconds = 1209600
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        // @formatter:off
        auth.authenticationProvider(authenticationProvider())
        // @formatter:on
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(customUserDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder())
        return authProvider
    }

    override fun configure(http: HttpSecurity) {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers(
                    Constants.Security.INDEX,
                    Constants.Security.LOGIN,
                    Constants.Security.REGISTER
                ).permitAll()
                .anyRequest().authenticated()
            .and().formLogin()
                .loginPage(Constants.Route.INDEX).permitAll()
                .loginProcessingUrl(Constants.Route.INDEX)
                .failureUrl("${Constants.Route.INDEX}}/?login_error=true")
            .and()
                .logout().permitAll()
                .logoutRequestMatcher(AntPathRequestMatcher(Constants.Security.LOGOUT, "GET"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl(Constants.Route.INDEX)
            .and()
                .rememberMe()
                    .key("secureTokenNotAllowedToShareInPublic")
                    .tokenValiditySeconds(defaultTokenValiditySeconds) // two weeks
        // @formatter:off
    }

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
                "/css/**",
                "/scripts/**",
                "/images/**",
                "/webjars/**",
                "/favicon.ico"
        )
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(defaultPasswordEncryptionStrength)
    }
}
