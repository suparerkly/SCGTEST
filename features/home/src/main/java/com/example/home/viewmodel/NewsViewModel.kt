package com.example.home.viewmodel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.home.Article
import com.example.core.remote.NetworkResult
import com.example.data.repository.NewsRepository
import com.example.home.state.NewsDetailState
import com.example.home.state.NewsUIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class NewsViewModel @Inject constructor(
    private val repository: NewsRepository,
) : ViewModel() {

    var search = ""

    private val _newsDetailUiState = MutableStateFlow(NewsDetailState())
    val newsDetailUiState: StateFlow<NewsDetailState> get() = _newsDetailUiState

    private val _loadingUiState = MutableStateFlow(true)
    val loadingUiState: StateFlow<Boolean> get() = _loadingUiState

    private val _newsUiState = MutableStateFlow(NewsUIState())
    val newsUiState: StateFlow<NewsUIState> get() = _newsUiState

    init {
        searchNews("tesla")
    }

    fun setNewsDetailUiState(detail: Article) {
        _newsDetailUiState.update {
            NewsDetailState(data = detail)
        }
    }

    fun searchNews(search: String) {
        viewModelScope.launch {
            try {
                repository.getNews(search).collect { resources ->
                    when (resources) {
                        is NetworkResult.Success -> {
                            _loadingUiState.update {
                                false
                            }
                            _newsUiState.update {
                                NewsUIState(resources.data)
                            }
                        }

                        is NetworkResult.Failure -> {
                            _loadingUiState.update {
                                false
                            }
                        }

                        is NetworkResult.Loading -> {
                            _loadingUiState.update {
                                true
                            }
                        }
                    }
                }
            } catch (ex: Exception) {
                Log.d("exception", "ERROR EXCEPTION$ex")
            }
        }
    }

}
