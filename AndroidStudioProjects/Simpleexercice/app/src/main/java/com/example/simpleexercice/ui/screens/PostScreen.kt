package com.example.simpleexercice.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simpleexercice.data.Post
import com.example.simpleexercice.ui.PostViewModel
import com.example.simpleexercice.ui.UiState
import androidx.compose.material3.Button


@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text=message, color=Color.Red)
    }
}

@Composable
fun PostList(posts: List<Post>, modifier : Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(8.dp)
    ) {
        items(items=posts) {post ->
            Card(
                modifier=modifier.fillMaxWidth().padding(6.dp)
            ) {
                Column(
                    modifier = modifier.padding(12.dp)
                ) {
                    Text(
                        text=post.title,
                        color = Color.Red
                    )
                    HorizontalDivider()
                    Text(text=post.body)

                }
            }
        }
    }
}

@Composable
fun PostScreen(postViewModel: PostViewModel, modifier: Modifier) {
    Column(modifier = modifier.fillMaxSize()) {

        // 1. Le bouton tout en haut
        Button(
            onClick = { postViewModel.testCreatePost() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Tester l'envoi POST")
        }

        // 2. Le contenu de la page juste en dessous
        when(val state = postViewModel.state.value) {
            is UiState.Loading -> LoadingView()
            is UiState.Success -> PostList(posts = state.posts, modifier = Modifier)
            is UiState.Error -> ErrorView(message = state.message)
        }
    }
}