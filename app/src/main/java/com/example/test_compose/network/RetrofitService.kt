package com.example.test_compose.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitService {
//    https://gnews.io/api/v4/top-headlines?token=d86b4c1e1b9be65fface7ce353120d73&lang=en
    @GET("top-headlines?&lang=en")
    suspend fun getService(
//        @Header("token") token: String,
        @Query("token") token: String
    ): SearchResponse
}