package com.example.home

import com.example.core.api.home.HomeAPIInterface
import com.example.core.remote.NetworkResult
import com.example.data.remote.NewsRemoteService
import com.example.data.repository.NewsRepository
import com.example.home.viewmodel.NewsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Mock
    lateinit var newsViewModel: NewsViewModel

    @Mock
    lateinit var repository: NewsRepository

    @Mock
    lateinit var remoteService: NewsRemoteService

    @Mock
    lateinit var apiService: HomeAPIInterface


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun myTest() = runTest {
        val repository = NewsRepository(remoteService)
        repository.getNews("tesla").collect { resource ->
            when (resource) {
                is NetworkResult.Success -> {
                }

                is NetworkResult.Failure -> {
                    println(null)
                }

                is NetworkResult.Loading -> {
                }
            }
        }
    }
}