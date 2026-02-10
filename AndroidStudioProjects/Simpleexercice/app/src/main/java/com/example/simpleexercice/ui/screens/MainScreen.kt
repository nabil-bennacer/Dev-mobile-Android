package com.example.simpleexercice.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ShowScore(modifier: Modifier = Modifier, mainScreenViewModel: MainScreenViewModel = viewModel()){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ){
        Text(
            text = "Score: ${mainScreenViewModel.uiState.score}"
        )
        OutlinedButton(
            onClick = {mainScreenViewModel.decrementScore()}
        ){Text("-1")}

        OutlinedButton(onClick = {mainScreenViewModel.incrementScore()}) {
            Text("+1")
        }
    }
}