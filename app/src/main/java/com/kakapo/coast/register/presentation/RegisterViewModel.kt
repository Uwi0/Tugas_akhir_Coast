package com.kakapo.coast.register.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakapo.coast.core.presentation.util.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(): ViewModel() {

    private var firstName: String = ""
    private var secondName: String = ""
    private var email: String = ""
    private var password: String = ""
    private var confirmedPassword: String = ""

    private val _state = MutableLiveData<RegisterViewState>()
    val state: LiveData<RegisterViewState> = _state

    init {
        _state.value = RegisterViewState()
    }

    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.EnteredFirstName -> {
                inputFirstNameForm(event.value)
            }
            is RegisterEvent.EnteredSecondName -> {
                inputSecondNameForm(event.value)
            }
            is RegisterEvent.EnteredEmail -> {
                inputEmailForm(event.value)
            }
            is RegisterEvent.EnteredPassword -> {
                inputPasswordForm(event.value)
            }
            is RegisterEvent.EnteredConfirmPass -> {
                inputConfirmPassForm(event.value)
            }
            RegisterEvent.Register -> {
                checkPasswordAreSame()
            }
        }
    }

    private fun register() {
        //TODO add register logic here
    }

    private fun checkPasswordAreSame(){
        if (password == confirmedPassword){
            _state.value = _state.value!!.copy(
                isPassWithConfirmPassSame = false
            )
            register()
        }else{
            _state.value = _state.value!!.copy(
                isPassWithConfirmPassSame = true,
                isSuccess = AuthState.Error("password and confirmed password are not same nyaaa :3")
            )
        }
    }

    private fun inputConfirmPassForm(confrimPass: String) {
        confirmedPassword = confrimPass
        if (confrimPass.isNotEmpty()){
            setConfirmPassState()
        }else{
            setNoConfirmPassState()
        }
    }

    private fun setConfirmPassState() {
        _state.value = state.value!!.copy(
            isConfirmPasswordEmpty = false
        )
    }

    private fun setNoConfirmPassState() {
        _state.value = state.value!!.copy(
            isConfirmPasswordEmpty = true
        )
    }

    private fun inputPasswordForm(value: String) {
        password = value
        if (value.isNotEmpty()){
            setPasswordState()
        }else{
            setNoPasswordState()
        }
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

    private fun inputEmailForm(value: String) {
        email = value
        if (value.isNotEmpty()){
            setEmailState()
        }else{
            setNoEmailState()
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

    private fun inputSecondNameForm(value: String){
        secondName = value
        if (value.isNotEmpty()){
            setSecondNameState()
        }else{
            setNoSecondNameState()
        }
    }

    private fun setSecondNameState() {
        _state.value = state.value!!.copy(
            isSecondNameEmpty = false
        )
    }

    private fun setNoSecondNameState() {
        _state.value = state.value!!.copy(
            isConfirmPasswordEmpty = true
        )
    }

    private fun inputFirstNameForm(value: String){
        firstName = value
        if (value.isNotEmpty()){
            setFirstNameState()
        }else{
            setNotFirstNameState()
        }
    }

    private fun setFirstNameState(){
        _state.value = state.value!!.copy(
            isFirstNameEmpty = false
        )
    }

    private fun setNotFirstNameState(){
        _state.value = state.value!!.copy(
            isFirstNameEmpty = true
        )
    }
}