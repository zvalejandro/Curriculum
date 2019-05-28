package com.zaraos.curriculum.di.components

import com.zaraos.curriculum.di.scopes.AppScope
import com.zaraos.curriculum.presentation.experience.ExperienceViewModel
import com.zaraos.curriculum.presentation.home.HomeViewModel
import dagger.Component

@AppScope
@Component(dependencies = [BaseNetworkComponent::class], modules = [AppComponent::class])
interface ActivityComponent {
    fun inject(homeViewModel: HomeViewModel)
    fun inject(experienceViewModel: ExperienceViewModel)
}

