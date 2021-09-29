package com.example.githubapp.core.utils

import com.example.githubapp.core.data.source.local.entity.UserEntity
import com.example.githubapp.core.data.source.remote.response.UserResponse
import com.example.githubapp.core.domain.model.User
import kotlin.collections.ArrayList

object DataMapper {
    fun mapUserResponsesToEntities(input: List<UserResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                it.id,
                it.login,
                it.avatarUrl,
                it.type,
                it.name,
                it.company,
                it.email,
                it.bio,
                it.publicRepos,
                it.publicGists,
                it.followers,
                it.following,
                it.createdAt,
                favorite = false
            )
            userList.add(user)
        }
        return userList
    }

    fun mapEntitiesToDomain(input: List<UserEntity>): List<User> {
        return input.map {
            User(
                it.id,
                it.login,
                it.avatarUrl,
                it.type,
                it.name,
                it.company,
                it.email,
                it.bio,
                it.publicRepos,
                it.publicGists,
                it.followers,
                it.following,
                it.createdAt,
                favorite = it.favorite,
            )
        }
    }

    fun mapDomainToEntity(input: User): UserEntity {
        return UserEntity(
            input.id,
            input.login,
            input.avatarUrl,
            input.type,
            input.name,
            input.company,
            input.email,
            input.bio,
            input.publicRepos,
            input.publicGists,
            input.followers,
            input.following,
            input.createdAt,
            favorite = input.favorite,
        )
    }
}