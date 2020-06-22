package com.example.movieu.movie.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.tmdbmovie.model.moviedetail.Genre
import com.example.movieu.R
import kotlinx.android.synthetic.main.ratings_item.view.*

class MovieGenreAdapter :
    ListAdapter<Genre, MovieGenreAdapter.GenreViewHolder>(GenreDiffUtilCallback()) {

    class GenreViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var value: TextView = root.text2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return GenreViewHolder(
            inflater.inflate(R.layout.ratings_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        getItem(position).let { genres ->
            holder.value.text = genres.name
        }
    }
}