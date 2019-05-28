package com.zaraos.curriculum.presentation.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.zaraos.curriculum.R
import com.zaraos.curriculum.base.BaseFragment
import com.zaraos.curriculum.base.extensions.getActivityViewModel
import com.zaraos.curriculum.data.source.Status

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    private lateinit var homeViewModel: HomeViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = getActivityViewModel()
        initView()
    }

    private fun initView() {
        homeViewModel.userInformation.observe(this, Observer {
            when (it?.status) {
                Status.SUCCESS -> {
                    it.data?.let { user ->

                    }
                }
                Status.ERROR -> {

                }
                Status.LOADING -> {
                }
            }
        })
    }
}