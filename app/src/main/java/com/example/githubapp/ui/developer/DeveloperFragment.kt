package com.example.githubapp.ui.developer

import android.os.Bundle
import android.view.*
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.githubapp.R
import com.example.githubapp.databinding.FragmentDeveloperBinding
import com.example.githubapp.utils.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class DeveloperFragment  : BaseFragment<FragmentDeveloperBinding>() {

    private val viewModel: DeveloperViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDeveloperBinding.inflate(inflater, container, false)
        return binding.root
    }

}