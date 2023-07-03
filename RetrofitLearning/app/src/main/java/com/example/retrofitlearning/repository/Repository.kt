package com.example.retrofitlearning.repository

import com.example.retrofitlearning.api.RetrofitInstance
import com.example.retrofitlearning.module.Post

class Repository {
    suspend fun getPost(): Post {
       return RetrofitInstance.api.getPost()
    }
}