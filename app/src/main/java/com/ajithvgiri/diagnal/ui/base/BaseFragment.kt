package com.ajithvgiri.diagnal.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ajithvgiri.diagnal.di.factory.AppViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var factory: AppViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }
}