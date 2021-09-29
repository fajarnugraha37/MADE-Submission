package com.example.githubapp.core.domain.usecase

import com.example.githubapp.core.data.Resource
import com.example.githubapp.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GithubAppUseCase {

    fun getAllUsers(sort: String): Flow<Resource<List<User>>>

    fun getUserFollowers(userName: String): Flow<Resource<List<User>>>

    fun getUserFollowing(username: String): Flow<Resource<List<User>>>

    fun getFavoriteUsers(sort: String): Flow<List<User>>

    fun getSearchUsers(search: String): Flow<Resource<List<User>>>

    fun setUserFavorite(user: User, state: Boolean)

    suspend fun setThemeSetting(isDarkMode: Boolean)

    fun getThemeSetting(): Flow<Boolean>
}