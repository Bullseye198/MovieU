package com.example.movieu.movie.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import com.example.movieu.R
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.movie.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieListAdapter(val event: MutableLiveData<MovieListEvent> = MutableLiveData()) :
    ListAdapter<Movie, MovieListAdapter.MovieViewHolder>
        (MovieDiffUtilCallback()) {

    class MovieViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var content: TextView = root.lbl_message
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
            holder.content.text = movie.text
            holder.itemView.setOnClickListener {
                event.value = MovieListEvent.OnMovieItemClick(position, movie.id)
            }
        }
    }
}