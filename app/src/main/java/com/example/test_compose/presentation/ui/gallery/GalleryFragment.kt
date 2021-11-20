package com.example.test_compose.presentation.ui.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.example.test_compose.ui.theme.ComposeTutorialTheme
import com.example.test_compose.utils.BROKEN_IMAGE
import com.example.test_compose.utils.loadPicture
import com.example.test_compose.view_model.NewsProperty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope

class GalleryFragment : Fragment(){

    var newsId: MutableState<NewsProperty?> = mutableStateOf(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            arguments?.getParcelable<NewsProperty>("newsId")?.let { nId ->
                newsId.value = nId
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeTutorialTheme {
                    newsId.value?.let { url ->
                    Gallery(news = url)
                }
            }
        }
    }
}

@Composable
fun Gallery(
    news: NewsProperty
){
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(1f) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RectangleShape)
                .pointerInput(
                    Unit
                ){
                    detectTransformGestures{_,_,zoom,rotation ->
                        scale.value *= zoom
                        rotationState.value += rotation
                    }
                }
        ){
            news.image?.let { url ->
                val image = loadPicture(url = url, defaultImage = BROKEN_IMAGE).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "NewsImage",
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .graphicsLayer(
                                scaleX = maxOf(.5f, minOf(3f, scale.value)),
                                scaleY = maxOf(.5f, minOf(3f, scale.value)),
                                rotationZ = rotationState.value),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
}