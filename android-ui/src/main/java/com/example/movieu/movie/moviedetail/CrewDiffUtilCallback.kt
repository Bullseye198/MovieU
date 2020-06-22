package com.example.movieu.movie.moviedetail

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.tmdbmovie.model.moviedetail.Crew

class CrewDiffUtilCallback : DiffUtil.ItemCallback<Crew>() {
    override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
        return oldItem == newItem
    }
}