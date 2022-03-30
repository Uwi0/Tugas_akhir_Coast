package com.kakapo.coast.core.presentation.util

sealed class AuthState {
    object FirstInit: AuthState()
    object Success: AuthState()
    data class Error(val value: String): AuthState()
}