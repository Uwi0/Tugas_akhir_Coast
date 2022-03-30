package com.kakapo.coast.register.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kakapo.coast.R
import com.kakapo.coast.core.presentation.util.AuthState
import com.kakapo.coast.core.presentation.util.Event
import com.kakapo.coast.core.presentation.util.shortToast
import com.kakapo.coast.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onRegister()
        setupUi()
    }

    private fun setupUi() {
        observerUiState()
    }

    private fun observerUiState() {
        viewModel.state.observe(viewLifecycleOwner) {
            updateUiState(it)
        }
    }

    private fun updateUiState(state: RegisterViewState) {
        val (
            loading,
            firstName,
            secondName,
            email,
            password,
            confirmPassword,
            samePassword,
            success,
        ) = state

        showLoadingProgressBar(loading)
        checkFirstNameIsEmpty(firstName)
        checkSecondNameIsEmpty(secondName)
        checkEmailIsEmpty(email)
        checkPasswordIsEmpty(password)
        checkConfirmPassword(confirmPassword)
        checkPasswordAndConfirmAreSame(samePassword)
        checkSuccessRegister(success)
    }

    private fun checkSuccessRegister(success: AuthState) {
        when(success){
            AuthState.FirstInit -> {
                Timber.d("first init nyaaa :3")
            }
            AuthState.Success -> {
                findNavController().popBackStack()
                Timber.d("success desu '<'")
            }
            is AuthState.Error -> {
                shortToast(R.string.register_fail)
                Timber.d("are system errror nyaaa :3 ${success.value}")
            }
        }
    }

    private fun checkPasswordAndConfirmAreSame(value: Boolean) {
        val samePassword = Event(value).getContentIfNotHandled() ?: return
        if (samePassword){
            shortToast(R.string.password_and_confirm_not_same)
        }
    }

    private fun checkConfirmPassword(value: Boolean) {
        val confirmPassword = Event(value).getContentIfNotHandled() ?: return
        if (confirmPassword){
            shortToast(R.string.confrim_password_empty)
        }
    }

    private fun checkPasswordIsEmpty(value: Boolean) {
        val password = Event(value).getContentIfNotHandled() ?: return
        if (password){
            shortToast(R.string.password_empty)
        }
    }

    private fun checkEmailIsEmpty(value: Boolean) {
        val email = Event(value).getContentIfNotHandled() ?: return
        if (email){
            shortToast(R.string.email_empty)
        }
    }

    private fun checkSecondNameIsEmpty(value: Boolean) {
        val secondName = Event(value).getContentIfNotHandled() ?: return
        if (secondName){
            shortToast(R.string.second_name_empty)
        }
    }

    private fun checkFirstNameIsEmpty(value: Boolean) {
        val firstName = Event(value).getContentIfNotHandled() ?: return
        if (firstName){
            shortToast(R.string.first_name_empty)
        }
    }

    private fun showLoadingProgressBar(loading: Boolean) {
        binding.registerProgressBar.isVisible = loading
    }

    private fun inputFirstName(){
        val firstName = binding.registerInputFirstName.text.toString()
        viewModel.onEvent(RegisterEvent.EnteredFirstName(firstName))
    }

    private fun inputSecondName(){
        val secondName = binding.registerInputSecondName.text.toString()
        viewModel.onEvent(RegisterEvent.EnteredSecondName(secondName))
    }

    private fun inputEmail(){
        val email = binding.registerInputEmail.text.toString()
        viewModel.onEvent(RegisterEvent.EnteredEmail(email))
    }

    private fun inputPassword(){
        val password = binding.registerInputPassword.text.toString()
        viewModel.onEvent(RegisterEvent.EnteredPassword(password))
    }

    private fun inputConfirmPassword(){
        val confirmPassword = binding.registerInputConfirmPassword.text.toString()
        viewModel.onEvent(RegisterEvent.EnteredConfirmPass(confirmPassword))
    }

    private fun onRegister(){
        binding.registerBtnRegister.setOnClickListener {
            inputFirstName()
            inputSecondName()
            inputEmail()
            inputPassword()
            inputConfirmPassword()
            viewModel.onEvent(RegisterEvent.Register)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}