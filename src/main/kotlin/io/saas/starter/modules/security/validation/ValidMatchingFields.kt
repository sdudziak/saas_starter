package io.saas.starter.modules.security.validation

import javax.validation.Constraint
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [MatchingFieldsConstraintValidator::class])
@Target(allowedTargets = [CLASS])
@Retention(RUNTIME)
annotation class ValidMatchingFields(
    val referenceFiled: String,
    val equalFiledName: String,
    val message: String,
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)
