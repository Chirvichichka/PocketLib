package com.chirvi.domain.usecase

class ConfirmPasswordUseCase() {
    operator fun invoke(
        password: String,
        passwordConfirm: String
    ): Boolean {
        return if (password.isNotEmpty() || passwordConfirm.isNotEmpty()) {
            password == passwordConfirm
        } else {
            false
        }
    }
}