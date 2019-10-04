package io.saas.starter.modules.application.controller

import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.ui.ExtendedModelMap
import org.springframework.web.context.request.WebRequest

internal class IndexControllerTest {

    @Test
    fun `index should return main script path`() {
        assertThat(IndexController().index(mockk<WebRequest>(), ExtendedModelMap())).contains("index")
    }
}
