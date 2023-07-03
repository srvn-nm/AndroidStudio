package com.example.retrofitlearning.api

import com.example.retrofitlearning.module.Post
import retrofit2.http.GET

interface SimpleApi {
    @GET("posts/1")
    suspend fun getPost(): Post
}