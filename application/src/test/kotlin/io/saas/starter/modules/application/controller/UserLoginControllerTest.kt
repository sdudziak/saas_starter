package io.saas.starter.modules.application.controller

import io.mockk.every
import io.mockk.mockk
import io.saas.starter.modules.security.signin.UserRegisterProcessService
import io.saas.starter.modules.security.signin.dto.User
import io.saas.starter.modules.security.signin.exceptions.EmailIsAlreadyInUseException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.validation.BindingResult
import org.springframework.validation.Errors
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.ModelAndView

internal class UserLoginControllerTest {

    companion object {
        val USER = User("string@email.com", "invalidPassword", "invalidPassword2")
    }

    @Test
    fun `login controller will complain for errors in login validation form`() {
        val controller = UserLoginController(getUserRegisterProcessServiceMock(true))
        val bindingResult = mockk<BindingResult>(relaxed = true)
        every { bindingResult.hasErrors() } answers { true }
        val output: ModelAndView? = controller.registerAction(
            USER,
            bindingResult,
            mockk<WebRequest>(),
            mockk<Errors>()
        )
        assertThat(output!!.viewName).isEqualTo("index")
    }

    @Test
    fun `login controller will complain if email is already taken during register tryout`() {
        val controller = UserLoginController(getUserRegisterProcessServiceMock(true))
        val bindingResult = mockk<BindingResult>(relaxed = true)
        every { bindingResult.hasErrors() } answers { false }
        val output: ModelAndView? = controller.registerAction(
            USER,
            bindingResult,
            mockk<WebRequest>(),
            mockk<Errors>()
        )
        assertThat(output!!.viewName).isEqualTo("index")
    }

    @Test
    fun `login controller allow to register without any errors`() {
        val controller = UserLoginController(getUserRegisterProcessServiceMock(false))
        val bindingResult = mockk<BindingResult>(relaxed = true)
        every { bindingResult.hasErrors() } answers { false }
        val output: ModelAndView? = controller.registerAction(
            USER,
            bindingResult,
            mockk<WebRequest>(),
            mockk<Errors>()
        )
        assertThat(output!!.viewName).isEqualTo("user/register/success")
    }

    private fun getUserRegisterProcessServiceMock(willComplain: Boolean): UserRegisterProcessService {
        val serviceMock = mockk<UserRegisterProcessService>(relaxed = true)

        if (willComplain) {
            every {
                serviceMock.registerNewUserAccount(any())
            } throws (EmailIsAlreadyInUseException("example@email.com"))
        } else {
            every { serviceMock.registerNewUserAccount(any()) } answers { }
        }

        return serviceMock
    }
}
