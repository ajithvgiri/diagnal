package com.ajithvgiri.diagnal.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajithvgiri.diagnal.di.factory.AppViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: AppViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

}