package com.githubexample.retrofit

import com.githubexample.data.GitHubUser
import com.githubexample.data.GitHubUserDetails
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {
    @GET("users")
    suspend fun getUsers(): List<GitHubUser>

    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): GitHubUserDetails
}