package com.example.movieu.movie.moviedetail

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.tmdbmovie.model.Genre

class GenreDiffUtilCallback : DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }
}