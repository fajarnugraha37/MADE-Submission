package com.example.githubapp.ui.home.search

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.R
import com.example.githubapp.core.data.Resource
import com.example.githubapp.core.domain.model.User
import com.example.githubapp.core.ui.UserAdapter
import com.example.githubapp.core.utils.SortUtils
import com.example.githubapp.databinding.FragmentSearchBinding
import com.example.githubapp.ui.MainViewModel
import com.example.githubapp.utils.BaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class SearchFragment : BaseFragment<FragmentSearchBinding>()  {
    private val mainViewModel: MainViewModel by viewModel()
    private val viewModel: SearchViewModel by viewModel()

    private lateinit var userAdapter: UserAdapter
    private var sort = SortUtils.USERNAME_ASC

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        userAdapter = UserAdapter()

        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
        userAdapter.onItemClick = { selectedData ->
//            val intent = Intent(activity, DetailActivity::class.java)
//            intent.putExtra(DetailActivity.EXTRA_MOVIE, selectedData)
//            startActivity(intent)
//            val action = Directions.actionCategoryProductItems2ToProductItem(null, it)
            val navController = Navigation.findNavController(requireView())
            navController?.navigate(R.id.action_navigation_search_to_navigation_detail)
        }

        binding.fabNameAsc.setOnClickListener {
            binding.famSort.close(true)
            sort = SortUtils.USERNAME_ASC
            setList(sort)
        }
        binding.fabNameDesc.setOnClickListener {
            binding.famSort.close(true)
            sort = SortUtils.USERNAME_DESC
            setList(sort)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setList(sort)
        setSearchList()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_menu, menu)

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            menu.findItem(R.id.action_light_mode).isVisible = true
        else
            menu.findItem(R.id.action_dark_mode).isVisible = true

        val searchManager = context?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
        searchView.queryHint = "Username Github"
        searchView.setOnCloseListener {
            binding.pbLoading.visibility = View.GONE
            binding.lavNotFound.visibility = View.GONE
            binding.tvNotFound.visibility = View.GONE
            setList(sort)
            true
        }
        searchView.setOnSearchClickListener {
            binding.pbLoading.visibility = View.GONE
            binding.lavNotFound.visibility = View.GONE
            binding.tvNotFound.visibility = View.GONE
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.setSearchQuery(newText)
                return false
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_dark_mode -> mainViewModel.setThemeSetting(true)
            R.id.action_light_mode -> mainViewModel.setThemeSetting(false)
            R.id.action_about -> findNavController().navigate(R.id.action_navigation_search_to_navigation_developer)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setList(sort: String) {
        viewModel.getUsers(sort).observe(viewLifecycleOwner, usersObserver)
    }

    private fun setSearchList() {
        viewModel.userResult.observe(viewLifecycleOwner, usersObserver)
    }

    private val usersObserver = Observer<Resource<List<User>>> { users ->
        if (users != null) {
            when (users) {
                is Resource.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                    binding.lavNotFound.visibility = View.GONE
                    binding.tvNotFound.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    userAdapter.setData(users.data)
                    if(users.data.isNullOrEmpty()) {
                        binding.lavNotFound.visibility = View.VISIBLE
                        binding.tvNotFound.visibility = View.VISIBLE
                    } else {
                        binding.lavNotFound.visibility = View.GONE
                        binding.tvNotFound.visibility = View.GONE
                    }
                }
                is Resource.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    binding.lavNotFound.visibility = View.VISIBLE
                    binding.tvNotFound.visibility = View.VISIBLE
                    Toast.makeText(context, getString(R.string.resource_error), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        fun instance() = SearchFragment()
    }
}