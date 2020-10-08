package com.ajithvgiri.diagnal.di.module

import com.ajithvgiri.diagnal.ui.MainActivity
import com.ajithvgiri.diagnal.ui.base.BaseActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeBaseActivity(): BaseActivity

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}