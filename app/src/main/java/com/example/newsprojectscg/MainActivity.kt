package com.example.newsprojectscg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.home.ui.DetailScreen
import com.example.home.ui.InitNewsScreen
import com.example.home.viewmodel.NewsViewModel
import com.example.newsprojectscg.ui.theme.NewsProjectSCGTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val mainViewModel: NewsViewModel = koinViewModel()

            NewsProjectSCGTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "firstscreen"
                ) {
                    composable("firstscreen") {
                        InitNewsScreen(navController, mainViewModel)
                    }
                    composable("secondscreen") {
                        DetailScreen(navController, mainViewModel)
                    }
                }
            }
        }
    }
}
