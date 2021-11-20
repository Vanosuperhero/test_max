package com.example.test_compose.presentation.ui.card

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavController
import androidx.navigation.findNavController
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

            }

    }
    }

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
                    ScreenCard(news = url, fragment = findNavController())
                    }
                }
            }
        }
    }
}


@Composable
fun ScreenCard(
    news: NewsProperty,
    fragment: NavController
){
    Column() {
        news.image?.let { url ->
            val image = loadPicture(url = url, defaultImage = BROKEN_IMAGE).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "NewsImage",
                    modifier = Modifier
                        .fillMaxWidth()
//                    .preferredHeight(225.dp)
                        .clickable(onClick = {
                            val bundle = Bundle()
                            bundle.putParcelable("newsId", news)
                            fragment.navigate(R.id.galleryFragment, bundle)
                        }),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Text(
            text = news.title,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.Start)
                .padding(15.dp),
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        news.content?.let {
            Text(
                text = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
                    .padding(12.dp),
                style = MaterialTheme.typography.subtitle1,
            )
        }
    }

}


