package com.example.simpleexercice.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {
    @GET("posts")
    suspend fun getPosts() : List<Post>
    @POST("posts")
    suspend fun createPost(@Body post: Post): Post
}