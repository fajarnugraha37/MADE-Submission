package com.example.githubapp.ui.developer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapp.core.domain.usecase.GithubAppUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class DeveloperViewModel(private val githubAppUseCase: GithubAppUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is About Developer Fragment"
    }
    val text: LiveData<String> = _text
}