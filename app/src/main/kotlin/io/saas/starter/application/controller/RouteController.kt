package io.saas.starter.application.controller

import io.saas.starter.Consts.Route.ASSETS_PATH
import io.saas.starter.Consts.Route.Redirect
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
class RouteController {

    @RequestMapping(value = [ASSETS_PATH])
    fun redirect(request: HttpServletRequest): String = Redirect.ASSETS
}
