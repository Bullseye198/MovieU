package com.example.movieu.movie.moviedetail

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.tmdbmovie.model.moviedetail.Cast

class CastDiffUtilCallback: DiffUtil.ItemCallback<Cast>() {
    override fun areItemsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Cast, newItem: Cast): Boolean {
        return oldItem == newItem
    }
}