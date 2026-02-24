package com.example.simpleexercice.data

class PostRepository {
    suspend fun getPosts(): List<Post> {
        return RetrofitInstance.api.getPosts()
    }

    suspend fun sendPost(newPost: Post): Post {
        // On appelle la fonction createPost de ton APIService
        return RetrofitInstance.api.createPost(newPost)
    }
}