package com.example.movieu.movie.movielist

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.tmdbmovie.model.Result

class MovieDiffUtilCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.title == newItem.title && oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}
