package com.example.test_compose.view_model

import android.util.Log
import com.example.test_compose.network.RetrofitService
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class ApiService(

    @SerializedName("title")
    val title: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("image")
    val image: String,


)



