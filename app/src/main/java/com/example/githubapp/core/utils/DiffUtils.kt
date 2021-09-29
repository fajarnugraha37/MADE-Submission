package com.example.githubapp.core.utils

import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.example.githubapp.core.domain.model.User

class DiffUtils(private val oldList: List<User>, private val newList: List<User>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val (id,
            name,
            avatarUrl,
            type,
            _, _, _, _, _, _, _, _, _,
            favorite
        ) = oldList[oldPosition]
        val (id1,
            name1,
            avatarUrl1,
            type1,
            _, _, _, _, _, _, _, _, _,
            favorite1
        ) = newList[newPosition]

        return id == id1
                && name == name1
                && avatarUrl == avatarUrl1
                && type == type1
                && favorite == favorite1
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        return super.getChangePayload(oldPosition, newPosition)
    }
}