package com.example.githubapp.ui.home.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubapp.core.data.Resource
import com.example.githubapp.core.domain.model.User
import com.example.githubapp.core.domain.usecase.GithubAppUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class FavoriteViewModel(private val githubAppUseCase: GithubAppUseCase) : ViewModel() {

    fun getFavoriteUsers(sort: String): LiveData<List<User>> {
        return githubAppUseCase.getFavoriteUsers(sort).asLiveData()
    }
}