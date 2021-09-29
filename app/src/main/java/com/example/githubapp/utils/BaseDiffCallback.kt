package com.example.githubapp.utils

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffCallback<T>(
    private var oldItems: ArrayList<T>,
    private var newItems: ArrayList<T>,
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }
}