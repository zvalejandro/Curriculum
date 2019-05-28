package com.zaraos.curriculum.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zaraos.curriculum.MyApplication
import com.zaraos.curriculum.data.repository.HomeRepository
import com.zaraos.curriculum.data.source.Resource
import com.zaraos.curriculum.di.components.DaggerActivityComponent
import com.zaraos.curriculum.domain.entity.UserEntity
import javax.inject.Inject

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
class HomeViewModel(application: Application) : AndroidViewModel(application) {
    @Inject
    lateinit var homeRepository: HomeRepository

    val _userInformation = MutableLiveData<Resource<UserEntity>>()
    val userInformation: LiveData<Resource<UserEntity>>
        get() = _userInformation

    init {
        DaggerActivityComponent.builder()
            .baseNetworkComponent(MyApplication.baseNetworkComponent)
            .build()
            .inject(this)

        homeRepository.getUserInformation {
            _userInformation.postValue(it)
        }
    }

    fun retry() {
        homeRepository.getUserInformation {
            _userInformation.postValue(it)
        }
    }
}