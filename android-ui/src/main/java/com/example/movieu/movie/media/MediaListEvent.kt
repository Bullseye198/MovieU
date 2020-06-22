package com.example.movieu.movie.media

sealed class MediaListEvent {
    data class OnMediaItemClick(
        val position: Int,
        val mediaId: Int
    ) : MediaListEvent()
}