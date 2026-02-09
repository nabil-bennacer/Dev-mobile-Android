package com.example.simpleexercice.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel


class MainScreenViewModel : ViewModel() {

    // Etat interne modifiable
    var score by mutableStateOf(0)
        private set

    fun incrementScore() {
        score += 1
    }

    fun decrementScore() {
        score -= 1
    }


}





