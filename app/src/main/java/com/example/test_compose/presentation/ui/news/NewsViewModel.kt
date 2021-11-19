package com.example.test_compose.presentation.ui.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_compose.di.NetworkModule
import com.example.test_compose.view_model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class NewsViewModel
@Inject
constructor(
    private val repository: NewsRepository,
//    private @Named("auth_token") val token: String,
): ViewModel(){


    val news: MutableState<List<NewsProperty>> = mutableStateOf(listOf())



    init {
        viewModelScope.launch {
            val result = repository.search(
                token = "d86b4c1e1b9be65fface7ce353120d73"
            )
            news.value = result
//            val getPropertiesDeferred = NetworkModule.provideRetrofitService().getService()

        }
    }
}

