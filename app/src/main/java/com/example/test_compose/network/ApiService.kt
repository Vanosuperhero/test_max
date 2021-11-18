package com.example.test_compose.view_model

import android.util.Log
import com.example.test_compose.network.RetrofitService
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class ApiService(

    @SerializedName("title")
    val title: String? = null,

//    @SerializedName("description")
//    val description: String? = null,
//
//    @SerializedName("content")
//    val content: String? = null,
//
//    @SerializedName("url")
//    val url: String? = null,

    @SerializedName("image")
    val image: String? = null,
//
//    @SerializedName("publishedAt")
//    val publishedAt: String? = null,

)

val service = Retrofit.Builder()
    .baseUrl("https://gnews.io/api/v4/")
    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
    .build()
    .create(RetrofitService::class.java)

