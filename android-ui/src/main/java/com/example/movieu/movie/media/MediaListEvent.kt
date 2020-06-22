package com.example.movieu.movie.media

import com.example.movieu.movie.moviedetail.MovieDetailEvent

sealed class MediaListEvent {
    data class OnMediaItemClick(
        val position: Int,
        val mediaId: Int
    ) : MediaListEvent()
}