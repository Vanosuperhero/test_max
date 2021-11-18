package com.example.test_compose.view_model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class NewsViewModel() : ViewModel() {


    val property: MutableState<List<String>> = mutableStateOf(listOf())


    init {
        val jjj = listOf("dd")
        property.value = jjj
//        viewModelScope.launch {
//            }

        }






}