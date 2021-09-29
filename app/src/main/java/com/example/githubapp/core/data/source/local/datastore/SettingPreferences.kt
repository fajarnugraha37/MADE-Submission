package com.example.githubapp.core.data.source.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferences(private val context: Context) {

    val getThemeSetting: Flow<Boolean>
        get() = context
            .dataStore
            .data
            .map {
                it[APP_THEME_KEY] ?: true
            }

    suspend fun setThemeSetting(isDarkMode: Boolean) {
        context
            .dataStore
            .edit {
                it[APP_THEME_KEY] = isDarkMode
            }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settingPrefs")
        private val APP_THEME_KEY = booleanPreferencesKey("app_theme")
    }
}