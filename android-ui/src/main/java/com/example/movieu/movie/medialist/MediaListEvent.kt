package com.example.movieu.movie.medialist

sealed class MediaListEvent {
    data class OnMediaItemClick(
        val position: Int,
        val mediaId: Int,
        val isSeries: Boolean
    ) : MediaListEvent()
}