package com.example.movieu.movie.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.domain.tmdbmovie.model.moviedetail.Crew
import com.example.movieu.R
import kotlinx.android.synthetic.main.credits_item.view.*

class MovieCrewAdapter :
    ListAdapter<Crew, MovieCrewAdapter.CrewViewHolder>(CrewDiffUtilCallback()) {

    class CrewViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var content: TextView = root.creditsContent
        var value: TextView = root.creditsValue
        var image: ImageView = root.imageCredits
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return CrewViewHolder(
            inflater.inflate(R.layout.credits_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        getItem(position).let { crew ->
            holder.content.text = crew.name
            holder.value.text = crew.job
            val profilePath = "https://image.tmdb.org/t/p/original/${crew.profilePath}"
            holder.image.load(profilePath)
        }
    }
}