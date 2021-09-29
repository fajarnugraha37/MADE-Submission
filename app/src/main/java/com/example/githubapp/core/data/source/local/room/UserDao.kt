package com.example.githubapp.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.githubapp.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @RawQuery(observedEntities = [UserEntity::class])
    fun getUsers(query: SupportSQLiteQuery): Flow<List<UserEntity>>

    @Query("SELECT * FROM userEntities WHERE login LIKE '%' || :search || '%'")
    fun getSearchUsers(search: String): Flow<List<UserEntity>>

    @RawQuery(observedEntities = [UserEntity::class])
    fun getFavoriteUsers(query: SupportSQLiteQuery): Flow<List<UserEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Update
    fun updateFavoriteUser(user: UserEntity)
}