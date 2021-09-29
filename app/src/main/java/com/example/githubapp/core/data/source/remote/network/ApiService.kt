package com.example.githubapp.core.data.source.remote.network

import com.example.githubapp.core.data.source.remote.response.ListUserResponse
import com.example.githubapp.core.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    companion object {
        private const val GITHUB_API_URL = "https://api.github.com/"
        private const val GITHUB_API_TOKEN = "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"
    }

    @Headers("Authorization: $GITHUB_API_TOKEN")
    @GET("search/users")
    suspend fun getSearchUser(
        @Query("q") search: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): ListUserResponse

    @Headers("Authorization: $GITHUB_API_TOKEN")
    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String,
                             @Query("page") page: Int,
                             @Query("per_page") perPage: Int,
    ): List<UserResponse>

    @Headers("Authorization: $GITHUB_API_TOKEN")
    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String,
                             @Query("page") page: Int,
                             @Query("per_page") perPage: Int,
    ): List<UserResponse>


    @Headers("Authorization: $GITHUB_API_TOKEN")
    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): UserResponse
}