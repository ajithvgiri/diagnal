package com.ajithvgiri.diagnal.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {
    @GET("content/{page}")
    fun fetchContents(@Path("page") page: Int): Deferred<Response<com.ajithvgiri.diagnal.data.model.Response>>
}