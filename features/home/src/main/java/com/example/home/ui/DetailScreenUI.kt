package com.example.home.ui

import android.os.Build
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.home.R
import com.example.home.viewmodel.NewsViewModel
import org.koin.androidx.compose.koinViewModel
import utils.getDateFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, mainViewModel: NewsViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(android.graphics.Color.parseColor("#49A596")),
                    titleContentColor = Color(android.graphics.Color.parseColor("#ffffff")),
                ),
                title = {
                    Text("Details")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ShowDetail(mainViewModel)
        }
    }
}


@Composable
fun ShowDetail(mainViewModel: NewsViewModel) {
    Column {
        AsyncImage(
            model = mainViewModel.newsDetailUiState.collectAsState().value.data?.urlToImage ?: "",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        )
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                mainViewModel.newsDetailUiState.collectAsState().value.data?.title ?: "".repeat(30),
                overflow = TextOverflow.Ellipsis,
                color = Color(android.graphics.Color.parseColor("#49A596")),
                fontSize = 25.sp,
                maxLines = 1,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp),
                textAlign = TextAlign.Start

            )
            Text(
                mainViewModel.newsDetailUiState.collectAsState().value.data?.description
                    ?: "".repeat(50),
                overflow = TextOverflow.Ellipsis,
                color = Color.Black,
                fontSize = 15.sp,
                maxLines = 6,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp),
                textAlign = TextAlign.Start
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Text(
                    "Updated: ${getDateFormatter(mainViewModel.newsDetailUiState.collectAsState().value.data?.publishedAt ?: "")}",
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray,
                    fontSize = 10.sp,
                    maxLines = 6,
                    modifier = Modifier
                        .padding(top = 10.dp),
                    textAlign = TextAlign.Start
                )
            } else {
                Text(
                    "Updated: ${mainViewModel.newsDetailUiState.collectAsState().value.data?.publishedAt ?: ""}",
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray,
                    fontSize = 10.sp,
                    maxLines = 6,
                    modifier = Modifier
                        .padding(top = 10.dp),
                    textAlign = TextAlign.Start
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}
