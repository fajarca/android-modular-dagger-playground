package io.fajarca.project.user.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.fajarca.project.user.databinding.ItemUserBinding
import io.fajarca.project.user.domain.entity.User

class UserRecyclerAdapter : ListAdapter<User, UserRecyclerAdapter.ViewHolder>(diffCallback) {
    
    private var onUserSelected: (User) -> Unit = {}
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onUserSelected)
    }

    class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User, onUserSelected: (User) -> Unit) {
            binding.tvEmail.text = user.email
            binding.tvName.text = user.name
            binding.root.setOnClickListener { onUserSelected(user) }
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    fun setOnUserSelected(block: (User) -> Unit) {
        this.onUserSelected = block
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }


}
