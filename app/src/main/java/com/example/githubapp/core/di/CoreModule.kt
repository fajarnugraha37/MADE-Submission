package com.example.githubapp.core.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import androidx.room.Room
import com.example.githubapp.core.data.GithubAppRepository
import com.example.githubapp.core.data.source.local.datastore.SettingPreferences
import com.example.githubapp.core.data.source.local.LocalDataSource
import com.example.githubapp.core.data.source.local.room.UserDatabase
import com.example.githubapp.core.data.source.remote.RemoteDataSource
import com.example.githubapp.core.data.source.remote.network.ApiService
import com.example.githubapp.core.domain.repository.IGithubAppRepository
import com.example.githubapp.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory {
        get<UserDatabase>().userDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            UserDatabase::class.java,
            "user.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}
val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single {
        SettingPreferences(androidContext())
    }
    single {
        LocalDataSource(get(), get())
    }
    single {
        RemoteDataSource(get())
    }
    factory {
        AppExecutors()
    }
    single<IGithubAppRepository> {
        GithubAppRepository(get(), get(), get())
    }
}