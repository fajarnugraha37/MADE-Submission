package com.example.githubapp.utils

interface BaseViewHolder<T> {
    fun onBind(item: T) : Unit
}