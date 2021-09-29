package com.example.githubapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.githubapp.R
import com.example.githubapp.utils.BaseActivity
import com.example.githubapp.databinding.ActivityMainAppBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : BaseActivity<ActivityMainAppBinding>() {
    private lateinit var  navController: NavController;
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.themeSetting.observe(this,
            { isDarkModeActive: Boolean ->
                if (isDarkModeActive)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                binding = ActivityMainAppBinding.inflate(layoutInflater)
                setContentView(binding.root)

                val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                navController = navHostFragment.navController
                navController.addOnDestinationChangedListener {controller, destination, arguments ->
                    if(destination.id == R.id.navigation_search || destination.id == R.id.navigation_favorite)
                        binding.flNavigation.visibility = View.VISIBLE
                    else
                        binding.flNavigation.visibility = View.GONE
                    Toast.makeText(applicationContext, "${destination.label}", Toast.LENGTH_SHORT).show()
                }

                binding.bncvNavigation.setNavigationChangeListener { _, position ->
                    when (position) {
                        0 -> navController.navigate(R.id.action_global_navigation_search)
                        1 -> navController.navigate(R.id.action_global_navigation_favorite)
                    }
                }
            })
    }

    override fun onNavigateUp(): Boolean {
        return true
    }
}