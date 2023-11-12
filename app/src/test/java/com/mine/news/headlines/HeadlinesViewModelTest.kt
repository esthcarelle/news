package com.mine.news.headlines

import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.mine.news.data.ApiService
import com.mine.news.model.Article
import com.mine.news.model.BaseModel
import com.mine.news.model.Source
import com.mine.news.network.UiState
import com.mine.news.repository.NewsRepository
import com.mine.news.ui.theme.home.HeadlinesViewModel
import com.mine.news.utils.DefaultDispatcherProvider
import com.mine.news.utils.DispatcherProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HeadlinesViewModelTest {

    // Mock the repository
    @MockK
    lateinit var apiService: ApiService

    @MockK
    lateinit var dispatcherProvider: DispatcherProvider

    @MockK
    lateinit var repository: NewsRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutinesRule = MainCoroutinesRule()

    private lateinit var headlinesViewModel: HeadlinesViewModel

    @Before
    fun setUp() {
        // Initialize the ViewModel with the mocked repository

        MockitoAnnotations.openMocks(this)
        apiService = mockk()
        repository = mockk()
        dispatcherProvider = DefaultDispatcherProvider()
    }

    @Test
    fun `test getHeadlines success`() = runTest {
        // Mock data for success scenario

        headlinesViewModel = HeadlinesViewModel(repository, dispatcherProvider)
        val source = "bbc"
        val expectedData = BaseModel(
            articles = listOf(
                Article(
                    source = Source("bbc-news", "BBC News"),
                    author = "Author",
                    title = "Title",
                    description = "Description",
                    url = "http://www.bbc.co.uk",
                    urlToImage = "http://www.bbc.co.uk/image.jpg",
                    publishedAt = "2023-11-10T10:00:00Z",
                    content = "Content"
                )
            ),
            status = "ok",
            totalResults = 1
        )

        // Mock repository response
        coEvery { repository.getHeadlines(source, "publishedAt") } returns flowOf(
            UiState.Success(
                expectedData
            )
        )
        mockkStatic(Uri::class)
        every { Uri.encode(any()) } answers { arg<String>(0) }

        // Execute the function
        headlinesViewModel.getHeadlines(source)

        // Assert the LiveData values
        headlinesViewModel.uiState.test {
            assertEquals(UiState.Success(expectedData), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test getHeadlines error`() = runTest {
        // Mock data for error scenario
        headlinesViewModel = HeadlinesViewModel(repository, dispatcherProvider)

        val source = "bbc"
        val expectedError = Exception("Test Error")

        // Mock repository response
        coEvery { repository.getHeadlines(source, "publishedAt") } returns flowOf(
            UiState.Error(
                expectedError.message
            )
        )

        // Execute the function
        headlinesViewModel.getHeadlines(source)

        headlinesViewModel.uiState.test {
            assertEquals(UiState.Error("Test Error"), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test getHeadlines empty`() = runTest {
        headlinesViewModel = HeadlinesViewModel(repository, dispatcherProvider)
        val source = "bbc"

        val expectedData = BaseModel(
            articles = emptyList(),
            status = "ok",
            totalResults = 0
        )

        // Mock repository response
        coEvery { repository.getHeadlines(source, "publishedAt") } returns flowOf(
            UiState.Success(
                expectedData
            )
        )

        // Execute the function
        headlinesViewModel.getHeadlines(source)

        // Assert the LiveData values
        headlinesViewModel.uiState.test {
            assertEquals(UiState.Success(expectedData), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

}