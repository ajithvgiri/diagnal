package com.ajithvgiri.diagnal.di.component

import com.ajithvgiri.diagnal.DiagnalApplication
import com.ajithvgiri.diagnal.di.module.ActivityModule
import com.ajithvgiri.diagnal.di.module.AppModule
import com.ajithvgiri.diagnal.di.module.FragmentModule
import com.ajithvgiri.diagnal.di.module.ViewModelModule
import com.ajithvgiri.diagnal.ui.base.BaseActivity
import com.ajithvgiri.diagnal.ui.base.BaseFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class,
        FragmentModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(app: DiagnalApplication)

    fun inject(app: BaseActivity)

    fun inject(app: BaseFragment)
}