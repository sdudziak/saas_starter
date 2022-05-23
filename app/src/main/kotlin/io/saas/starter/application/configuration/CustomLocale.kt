package io.saas.starter.application.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.CookieLocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor

@Configuration
class CustomLocale : WebMvcConfigurer {

    @Bean
    fun localeResolver(): LocaleResolver = CookieLocaleResolver()

    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val interceptor = LocaleChangeInterceptor()
        interceptor.paramName = "lang"
        interceptor.isIgnoreInvalidLocale = true
        return interceptor
    }

    @Bean
    fun localeMessageSource(): ResourceBundleMessageSource {
        val source = ResourceBundleMessageSource()
        source.setBasename("messages")
        source.setDefaultEncoding("UTF-8")
        source.setUseCodeAsDefaultMessage(true)
        return source
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(localeChangeInterceptor())
    }
}
