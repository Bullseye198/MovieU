package com.example.movieu.movie.medialist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.domain.tmdbmovie.model.MediaList
import com.example.movieu.R
import kotlinx.android.synthetic.main.movie_item.view.*

class MediaListAdapter(val event: MutableLiveData<MediaListEvent> = MutableLiveData()) :
    ListAdapter<MediaList, MediaListAdapter.MediaViewHolder>(MediaDiffUtilCallback()) {

    class MediaViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var content: TextView = root.lbl_message
        var year: TextView = root.lbl_year
        var image: ImageView = root.imv_list_item_icon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return MediaViewHolder(
            inflater.inflate(R.layout.movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        getItem(position).let { media ->
            holder.content.text = media.title
            holder.year.text = media.releaseDate
            val posterPath = "http://image.tmdb.org/t/p/w500/${media.posterPath}"
            holder.image.load(posterPath)
            if (media.posterPath == null) {
                holder.content.isVisible = true
                holder.year.isVisible = true
            }
            holder.itemView.setOnClickListener {
                event.value = MediaListEvent.OnMediaItemClick(position, media.id, media.isSeries)
            }
        }
    }
}