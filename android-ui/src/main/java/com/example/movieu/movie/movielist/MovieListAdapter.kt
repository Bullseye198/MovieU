package com.example.movieu.movie.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.domain.tmdbmovie.model.Result
import com.example.movieu.R
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieListAdapter(val event: MutableLiveData<MovieListEvent> = MutableLiveData()) :
    ListAdapter<Result, MovieListAdapter.MovieViewHolder>
        (MovieDiffUtilCallback()) {

    class MovieViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var content: TextView = root.lbl_message
        var year: TextView = root.lbl_year
        var image: ImageView = root.imv_list_item_icon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return MovieViewHolder(
            inflater.inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position).let { movie ->
            holder.content.text = movie.title
            holder.year.text = movie.releaseDate
            holder.image.load(movie.posterPath?.replace("http:", "https:"))
            holder.itemView.setOnClickListener {
                event.value = MovieListEvent.OnMovieItemClick(position, movie.id.toString())
            }
        }
    }
}