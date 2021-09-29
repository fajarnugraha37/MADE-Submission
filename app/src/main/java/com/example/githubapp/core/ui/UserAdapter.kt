package com.example.githubapp.core.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.githubapp.core.domain.model.User
import com.example.githubapp.core.utils.DiffUtils
import com.example.githubapp.databinding.ItemLayoutCardBinding
import java.util.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.MovieViewHolder>() {

    private var listData = ArrayList<User>()
    var onItemClick: ((User) -> Unit)? = null

    fun setData(newListData: List<User>?) {
        if (newListData == null) return
        val diffUtilCallback = DiffUtils(listData, newListData)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        listData.clear()
        listData.addAll(newListData)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getSwipedData(swipedPosition: Int): User = listData[swipedPosition]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemCardListBinding = ItemLayoutCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemCardListBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class MovieViewHolder(private val binding: ItemLayoutCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                tvUsername.text = user.login
                tvType.text = user.type

                Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
//                            pbItemLoading.visibility = GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
//                            pbItemLoading.visibility = GONE
                            return false
                        }
                    })
                    .into(ivAvatar)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}