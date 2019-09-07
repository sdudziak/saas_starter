package io.saas.starter.modules.security.signin.exceptions

class EmailIsAlreadyInUseException(email: String) : Exception("$email is already in use")
