package com.example.test_compose.presentation.ui.card

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.test_compose.R
import com.example.test_compose.presentation.ui.news.NewsViewModel
import com.example.test_compose.ui.theme.ComposeTutorialTheme
import com.example.test_compose.view_model.NewsProperty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CardFragment : Fragment(){

    private val viewModel: NewsViewModel by viewModels()
//    val newss = viewModel.newss
//    var newsId: MutableState<NewsProperty?> = mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        CoroutineScope(Main).launch {

//            delay(1000)
            arguments?.getInt("newsId")?.let { nId ->
//                viewModel.onTriggerEvent(CardEvent.GetCardEvent(nId))


//                Log.d("taggg", newsId.value?.title?: "dd")
//            }
//        }
    }}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

//            CoroutineScope(Main).launch {

//                delay(2000)
                setContent {

//
                    val newsProperty = viewModel.news.value

                val newsPropertyy = newsProperty


                    ComposeTutorialTheme {
//                    ScreenCard(text = "Hey, take a look at Jetpack Compose, it's great! ive UI. It simplifies and accelerates UI development on Android Less code, powerful tools, and intuitive Kotlin APIs :) It's the Android's modern toolkit for building native UI. It simplifies and accelerates UI development on Android Less code, powerful tools, and intuitive Kotlin APIs :) ive UI. It simplifies and accelerates UI development on Android Less code, powerful tools, and intuitive Kotlin APIs :)",
//                        painter = painterResource(id = R.drawable._7b98f6s_960),
//                        onClick = {})


                        Text(text = "Aboba ${newsProperty}")
                        Log.d("taggg", "${newsProperty}")
//                    }
                }
            }
        }
    }
}


@Composable
fun ScreenCard(
    painter: Painter,
    text: String,
    onClick: () -> Unit
){
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 12.dp,)
            .fillMaxWidth()
            .clickable(onClick = onClick)
        ,
        elevation = 8.dp,
    ) {
        Column{
            Image(
                painter = painter,
                contentDescription = "NewsImage",
                modifier = Modifier
                    .fillMaxWidth(),
//                    .preferredHeight(225.dp)
                contentScale = ContentScale.Crop
            )
            Text(
                text,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
                    .padding(6.dp),
                style = MaterialTheme.typography.body1,
            )
        }
    }
}