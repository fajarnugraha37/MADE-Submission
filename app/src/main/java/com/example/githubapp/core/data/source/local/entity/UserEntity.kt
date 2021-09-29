package com.example.githubapp.core.data.source.local.entity


import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "userEntities")
data class UserEntity(
    @PrimaryKey
    @NonNull
    var id: Int,
    val login: String,
    val avatarUrl: String,
    val type: String,
    val name: String?,
    val company: String?,
    val email: String?,
    val bio: String?,
    val publicRepos: Int?,
    val publicGists: Int?,
    val followers: Int?,
    val following: Int?,
    val createdAt: Date?,
    var favorite: Boolean = false,
)
