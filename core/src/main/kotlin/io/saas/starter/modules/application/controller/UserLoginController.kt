package io.saas.starter.modules.application.controller

import io.saas.starter.configuration.Constants
import io.saas.starter.modules.security.signin.UserRegisterProcessService
import io.saas.starter.modules.security.signin.dto.User
import io.saas.starter.modules.security.signin.exceptions.EmailIsAlreadyInUseException
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid

@Controller
class UserLoginController(val userRegisterProcessService: UserRegisterProcessService) {

    @PostMapping(path = [Constants.Route.REGISTER])
    fun registerAction(
        @ModelAttribute("user") @Valid accountDto: User,
        result: BindingResult,
        request: WebRequest,
        errors: Errors
    ): ModelAndView? {

        val errorView = ModelAndView("index", "user", accountDto)

        if (result.hasErrors()) {
            return errorView
        } else try {
            userRegisterProcessService.registerNewUserAccount(accountDto)
        } catch (emailTakenException: EmailIsAlreadyInUseException) {
            result.rejectValue(
                "email",
                "validation.invalid.email.already_taken",
                arrayOf(accountDto.email as Any),
                null
            )
            return errorView
        }

        return ModelAndView("user/register/success", "user", accountDto)
    }
}
