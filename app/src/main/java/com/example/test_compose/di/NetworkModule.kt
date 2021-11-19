package com.example.test_compose.di


import com.example.test_compose.network.ApiServiceMapper
import com.example.test_compose.network.RetrofitService
import com.example.test_compose.view_model.PropertyMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object  NetworkModule {

    @Singleton
    @Provides
    fun provideApiServiceMapper(): PropertyMapper{
        return PropertyMapper()

    }

    @Singleton
    @Provides
    fun provideRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://gnews.io/api/v4/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetrofitService::class.java)
    }

}