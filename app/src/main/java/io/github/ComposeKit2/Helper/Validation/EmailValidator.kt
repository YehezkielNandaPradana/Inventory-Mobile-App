package io.github.ComposeKit2.Helper.Validation

object EmailValidator {

    fun isValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS
            .matcher(email)
            .matches()
    }
}