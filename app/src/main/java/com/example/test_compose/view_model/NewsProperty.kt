package com.example.test_compose.view_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class NewsProperty (
    val title: String ,
    val image: String,
    val content: String
): Parcelable

//@Parcelize
//data class NewsResult (
//    val result: List<NewsProperty>
//): Parcelable