package com.example.simpleexercice.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleexercice.data.PostRepository
import com.example.simpleexercice.ui.UiState
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.example.simpleexercice.data.Post

class PostViewModel : ViewModel() {
    private val postRepository = PostRepository()
    private var internalState : MutableState<UiState> = mutableStateOf(UiState.Loading)
    val state : State<UiState> = internalState
    init {
        fetchPosts()
    }
    private fun fetchPosts() {
        viewModelScope.launch {
            internalState.value = UiState.Loading
            try {
                val posts = postRepository.getPosts()
                Log.d("TEST_API_GET", "Succès ! J'ai reçu ${posts.size} posts. Le premier est : ${posts[0].title}")
                internalState.value = UiState.Success(posts)
            } catch (e: Exception) {
                Log.d("TEST_API_GET", "Echec ! ${e.message}")
                internalState.value = UiState.Error("Failed to load posts "+e.message)
            }
        }
    }
    fun testCreatePost() {
        viewModelScope.launch {
            try {
                // On prépare un faux post à envoyer (l'ID 0 sera ignoré par le serveur)
                val monNouveauPost = Post(
                    userId = 1,
                    id = 0,
                    title = "Mon Super Titre",
                    body = "Ceci est un test POST"
                )

                // On envoie via le repository (que tu as codé à l'étape précédente)
                val reponseDuServeur = postRepository.sendPost(monNouveauPost)

                // On affiche la réponse dans Logcat pour vérifier !
                Log.d("TEST_API_POST", "Succès ! Le serveur a créé le post et lui a donné l'ID : ${reponseDuServeur.id}")

            } catch (e: Exception) {
                Log.e("TEST_API_POST", "Erreur lors du POST : ${e.message}")
            }
        }
    }
}