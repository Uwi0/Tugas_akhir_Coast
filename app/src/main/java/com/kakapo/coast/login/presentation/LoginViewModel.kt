package com.kakapo.coast.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<LoginViewState>()
    val state: LiveData<LoginViewState> = _state


    init {

        _state.value = LoginViewState()
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EnteredEmail -> {
                inputEmailForm(event.value)
            }
            is LoginEvent.EnteredPassword -> {
                inputPasswordForm(event.value)
            }
            LoginEvent.OnLogin -> {
                login()
            }
        }
    }

    private fun inputEmailForm(email: String) {
        if (email.trim().isNotEmpty()) {
            setEmailState()
        } else {
            setNoEmailState()
        }
    }

    private fun inputPasswordForm(password: String) {
        if (password.isNotEmpty()) {
            setPasswordState()
        } else {
            setNoPasswordState()
        }
    }

    private fun setEmailState() {
        _state.value = state.value!!.copy(
            isEmailEmpty = false
        )
    }

    private fun setNoEmailState() {
        _state.value = state.value!!.copy(
            isEmailEmpty = true
        )
    }

    private fun setPasswordState() {
        _state.value = state.value!!.copy(
            isPasswordEmpty = false
        )
    }

    private fun setNoPasswordState() {
        _state.value = state.value!!.copy(
            isPasswordEmpty = true
        )
    }

    private fun login() {
        //TODO add login logic
    }
}