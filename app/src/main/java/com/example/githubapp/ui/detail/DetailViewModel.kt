package com.example.githubapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.core.domain.usecase.GithubAppUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class DetailViewModel(private val githubAppUseCase: GithubAppUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Detail Fragment"
    }
    val text: LiveData<String> = _text
}