package com.example.githubapp.core.data

import com.example.githubapp.core.data.source.local.LocalDataSource
import com.example.githubapp.core.data.source.remote.RemoteDataSource
import com.example.githubapp.core.data.source.remote.network.ApiResponse
import com.example.githubapp.core.data.source.remote.response.UserResponse
import com.example.githubapp.core.domain.model.User
import com.example.githubapp.core.domain.repository.IGithubAppRepository
import com.example.githubapp.core.utils.AppExecutors
import com.example.githubapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class GithubAppRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGithubAppRepository {

    override fun getAllUsers(sort: String): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>() {
            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource
                    .getAllUsers(sort)
                    .map {
                        DataMapper.mapEntitiesToDomain(it)
                    }
            }

            override fun shouldFetch(data: List<User>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getAllUsers()
            }

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapUserResponsesToEntities(data)
                localDataSource.insertUsers(userList)
            }
        }.asFlow()

    override fun getUserFollowers(username: String): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>() {
            override fun loadFromDB(): Flow<List<User>> {
                return emptyFlow()
            }

            override fun shouldFetch(data: List<User>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getFollowers(username)
            }

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapUserResponsesToEntities(data)
                localDataSource.insertUsers(userList)
            }

        }.asFlow()

    override fun getUserFollowing(username: String): Flow<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>() {
            override fun loadFromDB(): Flow<List<User>> {
                return emptyFlow()
            }

            override fun shouldFetch(data: List<User>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getFollowing(username)
            }

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapUserResponsesToEntities(data)
                localDataSource.insertUsers(userList)
            }

        }.asFlow()

    override fun getFavoriteUsers(sort: String): Flow<List<User>> =
        localDataSource
            .getAllFavoriteUsers(sort)
            .map {
                DataMapper.mapEntitiesToDomain(it)
            }

    override fun getSearchUsers(search: String): Flow<Resource<List<User>>>  =
        object : NetworkBoundResource<List<User>, List<UserResponse>>() {
            override fun loadFromDB(): Flow<List<User>> {
                return localDataSource
                    .getUserSearch(search)
                    .map {
                        DataMapper.mapEntitiesToDomain(it)
                    }
            }

            override fun shouldFetch(data: List<User>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getSearchUsers(search)
            }

            override suspend fun saveCallResult(data: List<UserResponse>) {
                val userList = DataMapper.mapUserResponsesToEntities(data)
                localDataSource.insertUsers(userList)
            }
        }.asFlow()

    override fun setUserFavorite(user: User, state: Boolean) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors
            .diskIO()
            .execute {
                localDataSource.setUserFavorite(userEntity, state)
            }
    }

    override suspend fun setThemeSetting(isDarkMode: Boolean) {
        localDataSource.setThemeSetting(isDarkMode)
    }

    override fun getThemeSetting(): Flow<Boolean> =
        localDataSource .getThemeSetting
}