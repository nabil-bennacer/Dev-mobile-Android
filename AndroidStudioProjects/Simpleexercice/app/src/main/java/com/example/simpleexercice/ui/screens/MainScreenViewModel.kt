package com.example.simpleexercice.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


data class MainScreenUiState(
    var score: Int = 0
)

class MainScreenViewModel : ViewModel() {

    // Etat interne modifiable
    var uiState by mutableStateOf(MainScreenUiState())
        private set

    fun incrementScore() {
        uiState = uiState.copy(score = uiState.score + 1)
    }

    fun decrementScore() {
        uiState = uiState.copy(score = uiState.score - 1)
    }


}





