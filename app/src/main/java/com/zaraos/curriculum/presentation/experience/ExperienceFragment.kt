package com.zaraos.curriculum.presentation.experience

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaraos.curriculum.R
import com.zaraos.curriculum.base.BaseFragment
import com.zaraos.curriculum.base.extensions.getActivityViewModel
import com.zaraos.curriculum.data.source.Status
import kotlinx.android.synthetic.main.fragment_experience.*
import kotlinx.android.synthetic.main.view_empty_state.*

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
class ExperienceFragment : BaseFragment() {

    override fun getLayoutId(): Int = R.layout.fragment_experience
    private lateinit var experienceViewModel: ExperienceViewModel
    private val experienceAdapter = ExperienceAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        experienceViewModel = getActivityViewModel()
        experienceList.layoutManager = LinearLayoutManager(activity)
        experienceList.adapter = experienceAdapter
        initView()
    }

    private fun initView() {
        experienceViewModel.experience.observe(this, Observer {
            when (it?.status) {
                Status.SUCCESS -> {
                    experienceEmptyState.visibility = View.GONE
                    it.data?.let { experience ->
                        experienceAdapter.experienceList = experience.toMutableList()
                    }
                }
                Status.ERROR -> {
                    experienceEmptyState.visibility = View.VISIBLE
                    emptyStateTextRetry.setOnClickListener {
                        experienceViewModel.retry()
                    }
                }
                Status.LOADING -> {
                }
            }
        })
    }

}