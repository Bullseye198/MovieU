package com.example.movieu.movie.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.domain.tmdbmovie.model.Cast
import com.example.movieu.R
import kotlinx.android.synthetic.main.credits_item.view.*

class MovieCastAdapter :
    ListAdapter<Cast, MovieCastAdapter.CastViewHolder>(CastDiffUtilCallback()) {

    class CastViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var content: TextView = root.creditsContent
        var value: TextView = root.creditsValue
        var image: ImageView = root.imageCredits
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return CastViewHolder(
            inflater.inflate(R.layout.credits_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        getItem(position).let { cast ->
            holder.content.text = cast.name
            holder.value.text = cast.character
            val profilePath = "https://image.tmdb.org/t/p/original/${cast.profilePath}"
            holder.image.load(profilePath)
        }
    }
}