package com.githubexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.githubexample.data.GitHubUser
import com.githubexample.databinding.ItemUserBinding

class UserAdapter(
    private val onUserClicked: (GitHubUser) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var users: List<GitHubUser>? = null

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var userItem: GitHubUser? = null

        init {
            itemView.setOnClickListener {
                userItem?.let { it1 -> onUserClicked(it1) }
            }
        }

        fun bind(user: GitHubUser) {
            userItem = user
            binding.apply {
                username.text = user.login
                val userIdText = "ID: ${user.id}"
                userId.text = userIdText
                Glide.with(itemView.context)
                    .load(user.avatar_url)
                    .circleCrop()
                    .into(userAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        users?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int = users?.size ?: 0

    fun updateData(data: List<GitHubUser>) {
        users = data
        notifyDataSetChanged()
    }
}
