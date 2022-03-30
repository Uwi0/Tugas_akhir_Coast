package com.kakapo.coast.core.presentation.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.shortToast(@StringRes message: Int){
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

