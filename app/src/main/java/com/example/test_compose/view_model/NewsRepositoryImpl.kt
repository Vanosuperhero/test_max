package com.example.test_compose.view_model


import com.example.test_compose.view_model.PropertyMapper
import com.example.test_compose.network.RetrofitService
import dagger.Component
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton


class NewsRepositoryImpl(
    private val retrofitService: RetrofitService,
    private val mapper: PropertyMapper
    ): NewsRepository {


    override suspend fun search(token: String): List<NewsProperty> {
        return mapper.fromServiceList(retrofitService.getService(token = token).articles)
    }
}