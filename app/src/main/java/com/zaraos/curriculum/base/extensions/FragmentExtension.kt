package com.zaraos.curriculum.base.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

fun Fragment.toast(message: String, isLong: Boolean = false) {
    Toast.makeText(this.activity, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
}


inline fun <reified T : ViewModel> FragmentActivity.getViewModel() = ViewModelProviders.of(this).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.getViewModel() = ViewModelProviders.of(this).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.getActivityViewModel() = ViewModelProviders.of(activity!!).get(T::class.java)
