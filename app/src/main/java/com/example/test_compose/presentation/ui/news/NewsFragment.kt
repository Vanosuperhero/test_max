package com.example.test_compose.presentation.ui.news

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.Card

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.findNavController
import com.example.test_compose.R
import com.example.test_compose.SampleData

import com.example.test_compose.ui.theme.ComposeTutorialTheme
import com.example.test_compose.utils.BROKEN_IMAGE
import com.example.test_compose.utils.loadPicture
import com.example.test_compose.view_model.NewsProperty

import com.example.test_compose.view_model.PropertyMapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import java.nio.file.Files.size
import javax.inject.Inject



import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch



@AndroidEntryPoint
class NewsFragment : Fragment(){


    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            setContent {
                val news = viewModel.news.value
                ComposeTutorialTheme {
                    ListOfNews(news = news, fragment =  findNavController(), viewModel = viewModel)

                }
            }
        }
    }
}





//@Preview(showBackground = true,)
//@Composable
//fun FirstScreen(onClick: () -> Unit, text: String){
//    val allScreens = RallyScreen.values().toList()
//    var currentScreen by rememberSaveable { mutableStateOf(RallyScreen.Overview) }
//    val navController = rememberNavController()
//    Column(modifier = Modifier
//        .fillMaxWidth()
//        .padding(24.dp)) {
//            PhotographerCard(Modifier.padding(horizontal = 0.dp))
//        CityCard(text = "Москва", painter = painterResource(id = R.drawable._7b98f6s_960))
//    }

//    Button(onClick = { findNavController.navigate(R.id.cardFragment)}) {
//        Text(text = "toCardFragment")
//    }


//    ListOfNews(
//        text = "Hey, take a look at Jetpack Compose, it's great! It's the Android's modern toolkit for building native UI. It simplifies and accelerates UI development on Android Less code, powerful tools, and intuitive Kotlin APIs :)",
//        painter = painterResource(id = R.drawable._7b98f6s_960),
//        onClick = onClick
//    )
//}








@Composable
fun ListOfNews(
    news: List<NewsProperty>,
    fragment: NavController,
    viewModel: NewsViewModel
){
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.refresh() },
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            itemsIndexed(
                items = news
            ) { index, NewsProperty ->
                NewsCard(
                    news = NewsProperty,
                    onClick = {
                        val bundle = Bundle()
                        bundle.putParcelable("newsId", NewsProperty)
                        fragment.navigate(R.id.cardFragment, bundle)
                    }
                )
            }
        }
    }
}





@Composable
fun NewsCard(
    news: NewsProperty,
    onClick: () -> Unit
){
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(bottom = 12.dp,)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 8.dp,
    ) {
        Column {

            val image = loadPicture(url = news.image, defaultImage = BROKEN_IMAGE).value
            image?.let { img ->
                Log.d("taggImage","$img")
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "NewsImage",
                    modifier = Modifier
                        .fillMaxWidth(),
//                    .preferredHeight(225.dp)
                    contentScale = ContentScale.Crop
                )
            }



            Text(
                text = news.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
                    .padding(6.dp),
                style = MaterialTheme.typography.body1,
            )


        }
    }
}




//@Composable
//fun CityCard(
//    text: String,
//    painter: Painter
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = { /* Ignoring onClick */ }),
//        shape = RoundedCornerShape(10.dp),
//        elevation = 5.dp,
//    ) {
//        Box(){
//
//            Image(
//                painter = painter,
//                contentDescription = "Moscow",
//                contentScale = ContentScale.FillWidth,
//                modifier = Modifier.fillMaxWidth())
//
//            Text(
//                text = text,
//                modifier = Modifier
//                    .align(Alignment.BottomStart)
//                    .padding(12.dp),
//                style = TextStyle(color = Color.White, fontSize = 30.sp))
//        }
//    }
//}



//@ExperimentalAnimationApi
//@Composable
//fun ComposeCard() {
//        Card {
//            var expanded by remember { mutableStateOf(false)}
//            Column(Modifier.clickable {expanded = !expanded}) {
//                Image(painterResource(id = R.drawable.kremlin), contentDescription = null)
//                AnimatedVisibility(expanded) {
//                    Text(
//                        text = "Moscow",
//                        style = MaterialTheme.typography.h2,
//                    )
//                }
//            }
//        }
//}
//
//@Composable
//fun PhotographerCard(modifier: Modifier = Modifier) {
//    Row(
//        modifier
//            .padding(8.dp)
//            .clip(RoundedCornerShape(8.dp))
//            .background(MaterialTheme.colors.surface)
//            .clickable(onClick = { /* Ignoring onClick */ })
//            .padding(16.dp)
//    ) {
//        Surface(
//            modifier = Modifier.size(50.dp),
////            shape = CircleShape,
//            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
//        ) {
//            Image(painterResource(id = R.drawable.kremlin), contentDescription = null)
//        }
//
//        Text("Moscow", fontWeight = FontWeight.Bold)
//        Spacer(Modifier.width(30.dp))
//    }
//
//}


