package com.example.githubapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubapp.core.domain.usecase.GithubAppUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel(private val githubAppUseCase: GithubAppUseCase) : ViewModel() {

    val themeSetting = githubAppUseCase.getThemeSetting().asLiveData()

    fun setThemeSetting(isDarkMode: Boolean) {
        viewModelScope.launch {
            githubAppUseCase.setThemeSetting(isDarkMode)
        }
    }
}