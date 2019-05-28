package com.zaraos.curriculum.presentation.experience

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zaraos.curriculum.MyApplication
import com.zaraos.curriculum.data.repository.ExperienceRepository
import com.zaraos.curriculum.data.source.Resource
import com.zaraos.curriculum.di.components.DaggerActivityComponent
import com.zaraos.curriculum.domain.entity.ExperienceEntity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by alejandro.zaraos on 2019-05-28.
 */
class ExperienceViewModel(application: Application) : AndroidViewModel(application) {
    @Inject
    lateinit var experienceRepository: ExperienceRepository
    private val compositeDisposable = CompositeDisposable()

    val _experience = MutableLiveData<Resource<List<ExperienceEntity>>>()
    val experience: LiveData<Resource<List<ExperienceEntity>>>
        get() = _experience

    init {
        DaggerActivityComponent.builder()
            .baseNetworkComponent(MyApplication.baseNetworkComponent)
            .build()
            .inject(this)

        experienceRepository.getExperience {
            _experience.postValue(it)
        }
    }

    fun retry() {
        experienceRepository.getExperience {
            _experience.postValue(it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        experienceRepository.allCompositeDisposable.forEach { compositeDisposable.add(it) }
        compositeDisposable.clear()
    }
}