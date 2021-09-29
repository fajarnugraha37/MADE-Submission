package com.example.githubapp.ui.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.githubapp.core.data.Resource
import com.example.githubapp.core.domain.model.User
import com.example.githubapp.core.domain.usecase.GithubAppUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(private val githubAppUseCase: GithubAppUseCase) : ViewModel()  {

    private val querySearch = ConflatedBroadcastChannel<String>()

    fun setSearchQuery(search: String) {
        querySearch.offer(search)
    }

    fun getUsers(sort: String): LiveData<Resource<List<User>>> {
        return githubAppUseCase.getAllUsers(sort).asLiveData()
    }

    val userResult: LiveData<Resource<List<User>>>  = querySearch.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            githubAppUseCase.getSearchUsers(it)
        }.asLiveData()
}