package com.example.test_compose.view_model

import com.example.test_compose.network.ApiServiceMapper
import com.example.test_compose.network.SearchResponse

class PropertyMapper :ApiServiceMapper<ApiService, NewsProperty>{
    override fun mapFromService(service: ApiService): NewsProperty {
        return NewsProperty(
            title = service.title,
            image = service.image,
            content = service.content)
    }


    fun fromServiceList(initial: List<ApiService>): List<NewsProperty>{
        return initial.map { mapFromService(it) }
    }
}