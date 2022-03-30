package com.kakapo.coast.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kakapo.coast.R
import com.kakapo.coast.core.presentation.util.AuthState
import com.kakapo.coast.core.presentation.util.Event
import com.kakapo.coast.core.presentation.util.shortToast
import com.kakapo.coast.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()
    }

    private fun updateUi() {
        onLogin()
        onRegisterClicked()
        observeUpdateState()
    }

    private fun observeUpdateState() {
        viewModel.state.observe(viewLifecycleOwner) {
            Timber.d("this called nyaaa observer")
            updateViewScreenState(it)
        }
    }

    private fun updateViewScreenState(state: LoginViewState) {
        val (loading, isEmailEmpty, isPasswordEmpty, successLogin) = state
        showLoadingProgressBar(loading)
        checkEmailIsEmpty(isEmailEmpty)
        checkPasswordIsEmpty(isPasswordEmpty)
        checkSuccessLogin(successLogin)
    }

    private fun showLoadingProgressBar(loading: Boolean) {
        if (loading) {
            binding.loginProgressBar.isVisible = loading
        }
    }

    private fun checkSuccessLogin(successLogin: AuthState) {
        val login = Event(successLogin).getContentIfNotHandled() ?: return
        when (login) {
            AuthState.FirstInit -> {
                Timber.d("first init nyaaa :3")
            }
            AuthState.Success -> {
                Timber.d("success desu '<'")
            }
            is AuthState.Error -> {
                shortToast(R.string.login_fail)
                Timber.d("are system errror nyaaa :3 ${login.value}")
            }
        }
    }

    private fun checkEmailIsEmpty(emailEmpty: Boolean) {
        val email = Event(emailEmpty).getContentIfNotHandled() ?: return
        if (email) {
            Toast.makeText(requireContext(), R.string.email_empty, Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun checkPasswordIsEmpty(passwordEmpty: Boolean) {
        val password = Event(passwordEmpty).getContentIfNotHandled() ?: return
        if (passwordEmpty) {
            shortToast(R.string.password_empty)
        }
    }

    private fun inputEmail() {
        val email = binding.loginInputEmail.text.toString()
        viewModel.onEvent(LoginEvent.EnteredEmail(email))
    }

    private fun inputPassword() {
        val password = binding.loginInputPassword.text.toString()
        viewModel.onEvent(LoginEvent.EnteredPassword(password))
    }

    private fun onLogin() {
        binding.loginBtnLogin.setOnClickListener {
            inputEmail()
            inputPassword()
            viewModel.onEvent(LoginEvent.OnLogin)
        }
    }

    private fun onRegisterClicked() {
        binding.loginTextRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}