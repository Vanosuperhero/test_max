package com.example.test_compose.presentation.ui.news

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class NewsViewModel
@Inject
constructor(
    private val randomString: String
): ViewModel(){

    init {
        println("$randomString")
    }
}
