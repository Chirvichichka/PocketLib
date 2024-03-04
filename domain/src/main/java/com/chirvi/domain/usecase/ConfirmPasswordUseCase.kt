package com.chirvi.domain.usecase

private const val SHORT_PASSWORD_LENGTH = "Short password length"
private const val PASSWORD_MISMATCH = "Password mismatch"
private const val PASSWORD_IS_EMPTY = "Field is empty"
class ConfirmPasswordUseCase {
    operator fun invoke(
        password: String,
        passwordConfirm: String
    ): String {
        return if(password.length < 6) {
            SHORT_PASSWORD_LENGTH
        } else if (password.isEmpty() || passwordConfirm.isEmpty()) {
            PASSWORD_IS_EMPTY
        } else if(password != passwordConfirm) {
           PASSWORD_MISMATCH
        } else {
            ""
        }
    }
}