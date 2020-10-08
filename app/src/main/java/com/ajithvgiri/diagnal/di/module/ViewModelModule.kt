package com.ajithvgiri.diagnal.di.module

import androidx.lifecycle.ViewModel
import com.ajithvgiri.diagnal.di.factory.ViewModelKey
import com.ajithvgiri.diagnal.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

}