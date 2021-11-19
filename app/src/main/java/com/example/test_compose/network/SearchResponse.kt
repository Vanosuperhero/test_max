package com.example.test_compose.network

import com.example.test_compose.view_model.ApiService
import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @SerializedName("articles")
    var articles: List<ApiService>,

)