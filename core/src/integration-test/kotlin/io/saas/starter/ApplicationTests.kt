package io.saas.starter

import io.saas.starter.modules.security.validation.MatchingFieldsConstraintValidator
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [MatchingFieldsConstraintValidator::class])
@Tag("integrationTest")
class ApplicationTests {

    @Test
    fun `Application loads it's context`() {
    }
}

