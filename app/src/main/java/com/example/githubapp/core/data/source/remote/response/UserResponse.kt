package com.example.githubapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserResponse(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("login")
    val login: String,
    @field:SerializedName("avatar_url")
    val avatarUrl: String,
    @field:SerializedName("type")
    val type: String,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("company")
    val company: String?,
    @field:SerializedName("email")
    val email: String?,
    @field:SerializedName("bio")
    val bio: String?,
    @field:SerializedName("public_repos")
    val publicRepos: Int?,
    @field:SerializedName("public_gists")
    val publicGists: Int?,
    @field:SerializedName("followers")
    val followers: Int?,
    @field:SerializedName("following")
    val following: Int?,
    @field:SerializedName("created_at")
    val createdAt: Date?,
)
