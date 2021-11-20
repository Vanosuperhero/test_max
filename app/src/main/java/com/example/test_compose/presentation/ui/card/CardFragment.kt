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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
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
import com.example.test_compose.utils.BROKEN_IMAGE
import com.example.test_compose.utils.loadPicture
import com.example.test_compose.view_model.NewsProperty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CardFragment : Fragment(){



    var newsId: MutableState<NewsProperty?> = mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Main).launch {


            arguments?.getParcelable<NewsProperty>("newsId")?.let { nId ->
                newsId.value = nId
                if (newsId.value != null ){Log.d("taggg", "newsId")}
            }

    }}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

//            CoroutineScope(Main).launch {

                setContent {

                    ComposeTutorialTheme {
                        newsId.value?.let { url ->
                    ScreenCard(news = url, onClick = {})

                    }
                }
            }
        }
    }
}


@Composable
fun ScreenCard(
    news: NewsProperty,
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
            news.image?.let { url ->
                val image = loadPicture(url = url, defaultImage = BROKEN_IMAGE).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "NewsImage",
                        modifier = Modifier
                            .fillMaxWidth(),
//                    .preferredHeight(225.dp)
                        contentScale = ContentScale.Crop
                    )
                }

            }
            news.title?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                        .padding(6.dp),
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}