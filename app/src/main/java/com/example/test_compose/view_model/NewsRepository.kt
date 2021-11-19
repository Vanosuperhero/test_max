package com.example.test_compose.view_model

interface NewsRepository {

    suspend fun search(token: String): List<NewsProperty>


}