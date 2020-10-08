package com.ajithvgiri.diagnal.di.module

import android.app.Application
import com.ajithvgiri.diagnal.BuildConfig
import com.ajithvgiri.diagnal.network.RetrofitApi
import com.ajithvgiri.diagnal.utils.UiHelper
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {

            var interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                addInterceptor(interceptor)
            }
            addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Client-Service", "frontend-client")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Auth-Key", "naXw7ONz1zd92Y3b4dd363cyn4WpqA9o")
                    .header("Cache-Control", "public, max-age=" + 30)
                    .build()

                chain.proceed(request)
            }
        }
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(httpClient: OkHttpClient.Builder,moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(retrofit: Retrofit): RetrofitApi =
        retrofit.create(RetrofitApi::class.java)


    @Provides
    @Singleton
    fun uiHelper(application: Application) = UiHelper(application)

}