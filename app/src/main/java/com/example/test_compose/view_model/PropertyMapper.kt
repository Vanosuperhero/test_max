package com.example.test_compose.view_model

import android.util.Property
import com.example.test_compose.network.ApiServiceMapper
import com.google.gson.annotations.SerializedName

class PropertyMapper :ApiServiceMapper<ApiService, NewsProperty>{
    override fun mapFromService(service: ApiService): NewsProperty {
        return NewsProperty(
            title = service.title,
            image = service.image,
        )
    }

//    override fun mapFromProperty(property: NewsProperty): ApiService {
//        return ApiService(
//            title = property.title,
//            image = property.image,
//        )
//    }

    fun fromServiceList(initial: List<ApiService>): List<NewsProperty>{
        return initial.map { mapFromService(it) }
    }
}