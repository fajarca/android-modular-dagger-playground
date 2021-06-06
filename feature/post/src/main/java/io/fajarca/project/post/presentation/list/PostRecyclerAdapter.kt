package io.fajarca.project.post.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.fajarca.project.post.databinding.ItemPostBinding
import io.fajarca.project.post.domain.entity.Post


class PostRecyclerAdapter : ListAdapter<Post, PostRecyclerAdapter.ViewHolder>(diffCallback) {
    
    private var onPostSelected: (Post) -> Unit = {}
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onPostSelected)
    }

    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post, onPostSelected: (Post) -> Unit) {
            binding.tvBody.text = post.body
            binding.tvTitle.text = post.title
            binding.root.setOnClickListener { onPostSelected(post) }
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPostBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    fun setOnPostSelected(block: (Post) -> Unit) {
        this.onPostSelected = block
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }


}
