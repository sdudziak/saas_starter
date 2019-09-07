package io.saas.starter

import org.junit.experimental.categories.Category
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import io.saas.starter.modules.security.validation.MatchingFieldsConstraintValidator
import io.saas.starter.tests.categories.ContextAwareTest

@SpringBootTest(classes = [MatchingFieldsConstraintValidator::class])
@Category(ContextAwareTest::class)
class ApplicationTests {

    @Test
    fun contextLoads() {
    }
}
