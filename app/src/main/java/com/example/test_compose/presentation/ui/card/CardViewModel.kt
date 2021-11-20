package com.example.test_compose.presentation.ui.card

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_compose.view_model.NewsProperty
import com.example.test_compose.view_model.NewsRepository
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named



//
//@HiltViewModel
//class CardViewModel
//@Inject
//constructor(
//    private val newsRepository: NewsRepository,
//    @Assisted private val state: SavedStateHandle,
//): ViewModel(){
//    val newsProperty: MutableState<List<NewsProperty>?> = mutableStateOf(null)
//
//    val loading = mutableStateOf(false)
//
//    init {
//        state.get<Int>("state.key.newsId",)?.let { newsId ->
//            onTriggerEvent(CardEvent.GetCardEvent(newsId))
//        }
//    }
//
//    fun onTriggerEvent(event: CardEvent) {
//        viewModelScope.launch {
//            try {
//                when (event) {
//                    is CardEvent.GetCardEvent -> {
//                        if (newsProperty.value == null) {
//                            getNewsProperty(event.id)
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e("taggg", "onTriggerEvent: Exception ${e}, ${e.cause}")
//            }
//        }
//    }
//        private suspend fun getNewsProperty(id: Int){
//            loading.value = true
//            delay(1000)
//
//            val newsProperty = newsRepository.search(token = "d86b4c1e1b9be65fface7ce353120d73")
//            this.newsProperty.value = newsProperty
//
//            state.set<Int>("state.key.newsId",)
//
//            loading.value = false
//        }
//    }

