package com.zaraos.curriculum.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zaraos.curriculum.base.extensions.inflate

abstract class BaseFragment : Fragment() {

    abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getLayoutId() == 0) {
            throw RuntimeException("Invalid Layout ID")
        }
        return container?.inflate(getLayoutId())
    }
}