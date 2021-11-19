package com.example.test_compose.di

import com.example.test_compose.network.RetrofitService
import com.example.test_compose.view_model.NewsRepository
import com.example.test_compose.view_model.NewsRepositoryImpl
import com.example.test_compose.view_model.PropertyMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        retrofitService: RetrofitService,
        propertyMapper: PropertyMapper
    ): NewsRepository{
        return NewsRepositoryImpl(
            retrofitService = retrofitService, mapper = propertyMapper)
    }
}