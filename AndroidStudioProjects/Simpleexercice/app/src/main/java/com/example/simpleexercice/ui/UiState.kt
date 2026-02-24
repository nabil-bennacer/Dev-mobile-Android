package com.example.simpleexercice.ui

import com.example.simpleexercice.data.Post

sealed class UiState {
    object Loading: UiState()
    data class Success(val posts: List<Post>): UiState()
    data class Error(val message: String): UiState()
}