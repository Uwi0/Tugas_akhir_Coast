package com.kakapo.coast.login.presentation

import com.kakapo.coast.core.presentation.util.AuthState

data class LoginViewState(
    val loading: Boolean = false,
    val isEmailEmpty: Boolean = false,
    val isPasswordEmpty: Boolean = false,
    val successLogin: AuthState = AuthState.FirstInit
)