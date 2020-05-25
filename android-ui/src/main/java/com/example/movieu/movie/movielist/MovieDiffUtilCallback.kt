package com.example.movieu.movie.movielist

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.movie.model.Movie

class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.creationDate == newItem.creationDate
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.creationDate == newItem.creationDate
    }

}
