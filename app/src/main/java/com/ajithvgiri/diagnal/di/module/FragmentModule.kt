package com.ajithvgiri.diagnal.di.module

import com.ajithvgiri.diagnal.ui.base.BaseFragment
import com.ajithvgiri.diagnal.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

}