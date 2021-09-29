package com.example.githubapp.core.data.source.local

import com.example.githubapp.core.data.source.local.datastore.SettingPreferences
import com.example.githubapp.core.data.source.local.entity.UserEntity
import com.example.githubapp.core.data.source.local.room.UserDao
import com.example.githubapp.core.utils.SortUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class LocalDataSource(
    private val mUserDao: UserDao,
    private val settingPreferences: SettingPreferences
) {

    fun getAllUsers(sort: String): Flow<List<UserEntity>> {
        val query = SortUtils.getSortedQueryUsers(sort)
        return mUserDao.getUsers(query)
    }

    fun getAllFavoriteUsers(sort: String): Flow<List<UserEntity>> {
        val query = SortUtils.getSortedQueryFavoriteUsers(sort)
        return mUserDao.getFavoriteUsers(query)
    }

    fun getUserSearch(search: String): Flow<List<UserEntity>> {
        return mUserDao.getSearchUsers(search)
            .flowOn(Dispatchers.Default)
            .conflate()
    }

    suspend fun insertUsers(users: List<UserEntity>) = mUserDao.insertUsers(users)

    fun setUserFavorite(user: UserEntity, newState: Boolean) {
        user.favorite = newState
        mUserDao.updateFavoriteUser(user)
    }

    suspend fun setThemeSetting(isDarkMode: Boolean) {
        settingPreferences.setThemeSetting(isDarkMode)
    }

    val getThemeSetting: Flow<Boolean> = settingPreferences.getThemeSetting
}