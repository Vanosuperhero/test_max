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
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class NewsViewModel
@Inject
constructor(
    private val repository: NewsRepository,
): ViewModel() {

    val errore: MutableState<List<NewsProperty>> = mutableStateOf(
        listOf(
            NewsProperty(
                title = "Error",
                image = "",
                content = ""
            )
        )
    )

    val news: MutableState<List<NewsProperty>> = mutableStateOf(listOf())

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        init()
    }


    private fun init() {
            viewModelScope.launch {

                try {
                    val result = repository.search(
                        token = "d86b4c1e1b9be65fface7ce353120d73"
                    )
                    news.value = result
                } catch (e: Exception) {
            Log.d("tagg", "catch")
            news.value = errore.value

        }

            }


    }
    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun refresh() {
        // This doesn't handle multiple 'refreshing' tasks, don't use this
        viewModelScope.launch {
            // A fake 2 second 'refresh'
            _isRefreshing.emit(true)
            delay(2000)
            init()
            _isRefreshing.emit(false)
        }
    }
}

//repository, news, viewModelScope, errore
//repository: NewsRepository,
//news: MutableState<List<NewsProperty>>,
//viewModelScope: CoroutineScope,
//errore: MutableState<List<NewsProperty>>