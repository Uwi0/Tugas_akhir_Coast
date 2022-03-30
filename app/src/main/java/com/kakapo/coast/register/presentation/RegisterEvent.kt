package com.kakapo.coast.register.presentation

sealed class RegisterEvent{
    data class EnteredFirstName(val value: String): RegisterEvent()
    data class EnteredSecondName(val value: String): RegisterEvent()
    data class EnteredEmail(val value: String): RegisterEvent()
    data class EnteredPassword(val value: String): RegisterEvent()
    data class EnteredConfirmPass(val value: String): RegisterEvent()
    object Register: RegisterEvent()
}