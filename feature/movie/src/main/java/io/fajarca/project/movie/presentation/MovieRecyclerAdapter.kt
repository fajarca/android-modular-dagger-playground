package io.fajarca.project.movie.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.fajarca.project.movie.databinding.ItemMovieBinding
import io.fajarca.project.movie.domain.entity.Movie
import io.fajarca.project.movie.util.loadImage


class MovieRecyclerAdapter : ListAdapter<Movie, MovieRecyclerAdapter.ViewHolder>(diffCallback) {
    
    private var onMovieSelected: (Movie) -> Unit = {}
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onMovieSelected)
    }

    class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie, onMovieSelected: (Movie) -> Unit) {
            binding.ivMovie.loadImage(movie.imageUrl)
            binding.root.setOnClickListener { onMovieSelected(movie) }
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    fun setOnMovieSelected(block: (Movie) -> Unit) {
        this.onMovieSelected = block
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }


}
