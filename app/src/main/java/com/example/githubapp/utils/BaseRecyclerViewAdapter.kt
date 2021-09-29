package com.example.githubapp.utils

interface BaseRecyclerViewAdapter<T> {
    fun setItems(items : ArrayList<T>);
}