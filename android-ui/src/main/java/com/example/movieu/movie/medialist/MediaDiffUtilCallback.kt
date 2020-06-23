package com.example.movieu.movie.medialist

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.tmdbmovie.model.MediaList

class MediaDiffUtilCallback : DiffUtil.ItemCallback<MediaList>() {
    override fun areItemsTheSame(oldItem: MediaList, newItem: MediaList): Boolean {
        return oldItem.title == newItem.title && oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MediaList, newItem: MediaList): Boolean {
        return oldItem == newItem
    }

}