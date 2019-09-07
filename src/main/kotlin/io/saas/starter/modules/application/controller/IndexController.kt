package io.saas.starter.modules.application.controller

import io.saas.starter.configuration.Constants
import io.saas.starter.modules.security.signin.dto.User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.context.request.WebRequest

@Controller
class IndexController {

    @GetMapping(path = [Constants.Route.INDEX])
    fun index(webRequest: WebRequest, model: Model): String {
        model.addAttribute("user", User())
        return "index"
    }
}
