package com.example.movieu.movie.moviedetail

import android.view.View
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.movie.model.Movie
import com.example.domain.movie.model.Rating
import kotlinx.android.synthetic.main.ratings_item.view.*

class MovieRatingsAdapter(): ListAdapter<Movie, MovieRatingsAdapter.RatingViewHolder>() {

    class RatingViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var content: TextView = root.text1
    }
}

