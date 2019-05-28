package com.zaraos.curriculum.base.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

inline fun ConstraintLayout.updateParams(
    constraintSet: ConstraintSet = ConstraintSet(),
    updates: ConstraintSet.() -> Unit
) {
    constraintSet.clone(this)
    constraintSet.updates()
    constraintSet.applyTo(this)
}


inline fun <reified T : ViewGroup.LayoutParams> View.getParams() = this.layoutParams as T