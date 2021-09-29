package com.example.githubapp.core.domain.usecase

import com.example.githubapp.core.data.Resource
import com.example.githubapp.core.domain.model.User
import com.example.githubapp.core.domain.repository.IGithubAppRepository
import kotlinx.coroutines.flow.Flow

class GithubAppInteractor(
    private val iGithubAppRepository: IGithubAppRepository
) : GithubAppUseCase {

    override fun getAllUsers(sort: String): Flow<Resource<List<User>>> =
        iGithubAppRepository.getAllUsers(sort)

    override fun getUserFollowers(userName: String): Flow<Resource<List<User>>> =
        iGithubAppRepository.getUserFollowers(userName)

    override fun getUserFollowing(userName: String): Flow<Resource<List<User>>> =
        iGithubAppRepository.getUserFollowing(userName)

    override fun getFavoriteUsers(sort: String): Flow<List<User>> =
        iGithubAppRepository.getFavoriteUsers(sort)

    override fun getSearchUsers(search: String): Flow<Resource<List<User>>> =
        iGithubAppRepository.getSearchUsers(search)

    override fun setUserFavorite(user: User, state: Boolean) =
        iGithubAppRepository.setUserFavorite(user, state)

    override suspend fun setThemeSetting(isDarkMode: Boolean) {
        iGithubAppRepository.setThemeSetting(isDarkMode)
    }

    override fun getThemeSetting(): Flow<Boolean> =
        iGithubAppRepository.getThemeSetting()
}