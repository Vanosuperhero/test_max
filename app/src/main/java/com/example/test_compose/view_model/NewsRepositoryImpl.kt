package com.example.test_compose.view_model

import com.example.test_compose.network.ApiServiceMapper
import com.example.test_compose.network.RetrofitService

class NewsRepositoryImpl(
    private val retrofitService: RetrofitService,
    private val mapper: PropertyMapper
    ): NewsRepository {
    override suspend fun search(token: String): List<NewsProperty> {
        val result = retrofitService.getService(token).articles
        return mapper.fromServiceList(result)
    }
}