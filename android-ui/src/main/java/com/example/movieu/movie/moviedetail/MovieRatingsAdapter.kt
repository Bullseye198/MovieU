package com.example.movieu.movie.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.movie.model.Rating
import com.example.movieu.R
import kotlinx.android.synthetic.main.ratings_item.view.*

class MovieRatingsAdapter :
    ListAdapter<Rating, MovieRatingsAdapter.RatingViewHolder>(RatingsDiffUtilCallback()) {

    class RatingViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var source: TextView = root.text1
        var value: TextView = root.text2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return RatingViewHolder(
            inflater.inflate(R.layout.ratings_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        getItem(position).let { ratings ->
            holder.source.text = ratings.source
            holder.value.text = ratings.value
        }
    }
}