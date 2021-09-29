package com.example.githubapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubapp.core.data.source.local.entity.UserEntity
import com.example.githubapp.core.utils.Converter

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}