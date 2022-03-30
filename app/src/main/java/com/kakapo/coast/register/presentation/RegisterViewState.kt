package com.kakapo.coast.register.presentation

import com.kakapo.coast.core.presentation.util.AuthState

data class RegisterViewState(
    val loading: Boolean = false,
    val isFirstNameEmpty: Boolean = false,
    val isSecondNameEmpty: Boolean = false,
    val isEmailEmpty: Boolean = false,
    val isPasswordEmpty: Boolean = false,
    val isConfirmPasswordEmpty: Boolean = false,
    val isPassWithConfirmPassSame: Boolean = false,
    val isSuccess: AuthState = AuthState.FirstInit,
)