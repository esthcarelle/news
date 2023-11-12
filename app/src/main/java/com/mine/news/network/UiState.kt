package com.mine.news.network


/**
 * Data state for processing api response Loading, Success and Error
 */
sealed class UiState<out R> {
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val exceptionMessage: String?) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}