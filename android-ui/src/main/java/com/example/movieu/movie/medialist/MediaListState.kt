package com.example.movieu.movie.medialist

import com.example.domain.tmdbmovie.model.MediaList


data class MediaListState(
    val feed: List<MediaList>? = null
)
