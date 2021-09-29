package com.example.githubapp.di
import com.example.githubapp.core.domain.usecase.GithubAppInteractor
import com.example.githubapp.core.domain.usecase.GithubAppUseCase
import com.example.githubapp.ui.detail.DetailViewModel
import com.example.githubapp.ui.developer.DeveloperViewModel
import com.example.githubapp.ui.home.favorite.FavoriteViewModel
import com.example.githubapp.ui.MainViewModel
import com.example.githubapp.ui.home.search.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<GithubAppUseCase> { GithubAppInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DeveloperViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}