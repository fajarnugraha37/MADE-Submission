package com.example.githubapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter (
    private val activity: AppCompatActivity,
    private val tabs: List<Map<String, Any>>,
) : FragmentStateAdapter(activity){

    override fun createFragment(position: Int): Fragment {
        return tabs[position][FRAGMENT] as Fragment
    }

    override fun getItemCount(): Int {
        return tabs.size
    }

    fun getTitles(): List<String> {
        var titles = arrayListOf<String>();
        for (item in tabs)
            titles.add(item[TITLE].toString())
        return titles
    }

    companion object {
        const val FRAGMENT: String = "fragment"
        const val TITLE: String = "title"
    }
}