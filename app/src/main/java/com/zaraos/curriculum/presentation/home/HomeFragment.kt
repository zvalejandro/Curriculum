package com.zaraos.curriculum.presentation.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaraos.curriculum.R
import com.zaraos.curriculum.base.BaseFragment
import com.zaraos.curriculum.base.extensions.getActivityViewModel
import com.zaraos.curriculum.data.source.Status
import com.zaraos.curriculum.domain.entity.RecyclerItem
import com.zaraos.curriculum.presentation.home.HomeAdapter.Companion.TYPE_BANNER
import com.zaraos.curriculum.presentation.home.HomeAdapter.Companion.TYPE_EXPERIENCE
import com.zaraos.curriculum.presentation.home.HomeAdapter.Companion.TYPE_SUMMARY
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_empty_state.*

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    private lateinit var homeViewModel: HomeViewModel
    private val homeAdapter = HomeAdapter {
        openExperience(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = getActivityViewModel()
        homeList.layoutManager = LinearLayoutManager(activity)
        homeList.adapter = homeAdapter
        initView()
    }

    private fun initView() {
        homeViewModel.userInformation.observe(this, Observer {
            when (it?.status) {
                Status.SUCCESS -> {
                    homeEmptyState.visibility = View.GONE
                    it.data?.let { user ->
                        val list: ArrayList<RecyclerItem> = ArrayList()
                        list.add(RecyclerItem(TYPE_BANNER, user))

                        user.professionalSummary?.let { list.add(RecyclerItem(TYPE_SUMMARY, it)) }
                        user.experience?.let { list.add(RecyclerItem(TYPE_EXPERIENCE, it)) }
                        homeAdapter.homeList = list
                    }
                }
                Status.ERROR -> {
                    homeEmptyState.visibility = View.VISIBLE
                    emptyStateTextRetry.setOnClickListener {
                        homeViewModel.retry()
                    }
                }
                Status.LOADING -> {
                }
            }
        })
    }

    private fun openExperience(type: Int) {
        this.view?.let {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_garageFragment)
        }
    }
}