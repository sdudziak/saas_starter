package io.saas.starter.modules.security.validation

import io.saas.starter.modules.security.signin.dto.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.test.context.web.WebAppConfiguration
import javax.validation.Validation
import javax.validation.Validator

@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigContextLoader::class)
internal class UserDtoConstraintValidatorTest {

    private lateinit var validator: Validator

    private val matchingPasswordFieldsCases = listOf(
        User("abc@mail.com", "ValidP4ssW0_rD", "ValidP4ssW0_rD") to true,
        User("abc@mail.com", "ValidP4ssW0_rD_2", "ValidP4ssW0_rD_2") to true,
        User("abc@mail.com", "ValidP4ssW0_rD_3", "ValidP4ssW0_rD_3") to true
    )

    private val notMatchingPasswordFieldsCases = listOf(
        User("abc@mail.com", "", "ValidP4ssW0_rD_2") to false,
        User("abc@mail.com", "ValidP4ssW0_rD", "") to false,
        User("abc@mail.com", "ValidP4ssW0_rD", "ValidP4ssW0_rD_2") to false,
        User("abc@mail.com", "ValidP4ssW0_rD_2", "ValidP4ssW0_rD_3") to false,
        User("abc@mail.com", "ValidP4ssW0_rD_3", "ValidP4ssW0_rD") to false
    )

    private val validMailCases = listOf(
        User("abc@mail.com", "ValidP4ssW0_rD", "ValidP4ssW0_rD") to true,
        User("test@mail.com.pl", "ValidP4ssW0_rD", "ValidP4ssW0_rD") to true,
        User("super+user@gmail.com", "ValidP4ssW0_rD", "ValidP4ssW0_rD") to true,
        User("super.globa_4333l+user@gmail.com", "ValidP4ssW0_rD", "ValidP4ssW0_rD") to true
    )

    private val invalidMailCases = listOf(
        User("@test@mail.com.pl", "ValidP4ssW0_rD", "ValidP4ssW0_rD") to false,
        User(":superuser@gmail.com", "ValidP4ssW0_rD", "ValidP4ssW0_rD") to false,
        User("gmail.com", "ValidP4ssW0_rD", "ValidP4ssW0_rD") to false,
        User("http://wp.pl", "ValidP4ssW0_rD", "ValidP4ssW0_rD") to false
    )

    private val validPasswordCases = listOf(
        User("test@mail.pl", "ValidP4ssW0_rD", "ValidP4ssW0_rD") to true
    )

    private val invalidPasswordCases = listOf(
        User("test@mail.pl", "word", "word") to false,
        User("test@mail.pl", "Sh0r^", "Sh0r^") to false,
        User("test@mail.pl", "No_Numbers", "No_Numbers") to false,
        User("test@mail.pl", "", "") to false
    )

    @BeforeEach
    fun initTest() {
        validator = Validation.buildDefaultValidatorFactory().validator
    }

    @TestFactory
    fun `User DTO validation is working as expected`() = matchingPasswordFieldsCases
        .union(notMatchingPasswordFieldsCases)
        .union(validMailCases)
        .union(invalidMailCases)
        .union(validPasswordCases)
        .union(invalidPasswordCases)
        .map { (input, expected) ->
            DynamicTest.dynamicTest(
                "Passwords ${input.password} and ${input.matchingPassword} are valid: $expected"
            ) {
                val result = validator.validate(input)
                Assertions.assertSame(expected, result.isEmpty())
            }
        }
}
