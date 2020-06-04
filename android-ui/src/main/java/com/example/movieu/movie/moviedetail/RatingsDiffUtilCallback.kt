package com.example.movieu.movie.moviedetail

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.movie.model.Rating

class RatingsDiffUtilCallback : DiffUtil.ItemCallback<Rating>() {
    override fun areItemsTheSame(oldItem: Rating, newItem: Rating): Boolean {
        return oldItem.source == newItem.source
    }

    override fun areContentsTheSame(oldItem: Rating, newItem: Rating): Boolean {
        return oldItem == newItem
    }
}