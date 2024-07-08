package com.example.home.ui

import android.os.Build
import android.view.KeyEvent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.core.model.home.News
import com.example.home.R
import com.example.home.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel
import utils.getDateFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InitNewsScreen(navController: NavController, mainViewModel: NewsViewModel) {
    val loadingUiState = mainViewModel.loadingUiState.collectAsState()
    val newsState = mainViewModel.newsUiState.collectAsState()

    var loading by remember { mutableStateOf(false) }

    loading = loadingUiState.value

    if (loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieLoading(
                modifier = Modifier
                    .size(200.dp)
            )
        }
    } else {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(android.graphics.Color.parseColor("#49A596")),
                        titleContentColor = Color(android.graphics.Color.parseColor("#ffffff")),
                    ),
                    title = {
                        Text("News")
                    }
                )
            },
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                newsState.value.data?.let {
                    NewsListScreen(it, mainViewModel, navController)
                }
            }

        }
    }
}

@Composable
fun LottieLoading(modifier: Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.animation_loading)
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = 1,
        isPlaying = true,
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier,
        alignment = Alignment.Center
    )
}

@Composable
fun NewsListScreen(news: News, viewModel: NewsViewModel, navController: NavController) {
    val focusManager = LocalFocusManager.current
    var search by remember {
        mutableStateOf(viewModel.search)
    }

    OutlinedTextField(
        value = search,
        singleLine = true,
        onValueChange = { search = it },
        keyboardActions = KeyboardActions(
            onDone = {
                viewModel.search = search
                viewModel.searchNews(search)
                focusManager.clearFocus()
            }
        ),
        leadingIcon = { Icon(Icons.Default.Search, "") },
        modifier = Modifier
            .onKeyEvent {
                if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                    viewModel.search = search
                    viewModel.searchNews(search)
                    focusManager.clearFocus()
                    true
                }
                false
            }
            .fillMaxWidth()
            .padding(5.dp)
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        items(news.articles?.size ?: 0) { index ->
            if (!news.articles?.get(index)?.urlToImage.isNullOrEmpty()) {
                Column(
                    modifier = Modifier.clickable {
                        news.articles?.get(index)?.let {
                            viewModel.setNewsDetailUiState(it)
                        }
                        navController.navigate("secondscreen")
                    }) {
                    AsyncImage(
                        model = news.articles?.get(index)?.urlToImage ?: "",
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16f / 9f)
                            .padding(30.dp)
                    )
                    Column {
                        Text(
                            news.articles?.get(index)?.title ?: "".repeat(30),
                            overflow = TextOverflow.Ellipsis,
                            color = Color(android.graphics.Color.parseColor("#49A596")),
                            fontSize = 25.sp,
                            maxLines = 1,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 10.dp),
                            textAlign = TextAlign.Center

                        )
                        Text(
                            news.articles?.get(index)?.description ?: "".repeat(50),
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black,
                            fontSize = 15.sp,
                            maxLines = 6,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 10.dp),
                            textAlign = TextAlign.Center
                        )
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            Text(
                                "Updated: ${getDateFormatter(news.articles?.get(index)?.publishedAt ?: "")}",
                                overflow = TextOverflow.Ellipsis,
                                color = Color.Gray,
                                fontSize = 10.sp,
                                maxLines = 6,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 10.dp),
                                textAlign = TextAlign.Center
                            )
                        } else {
                            Text(
                                "Updated: ${news.articles?.get(index)?.publishedAt ?: ""}",
                                overflow = TextOverflow.Ellipsis,
                                color = Color.Gray,
                                fontSize = 10.sp,
                                maxLines = 6,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 10.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
            }
        }
    }
}