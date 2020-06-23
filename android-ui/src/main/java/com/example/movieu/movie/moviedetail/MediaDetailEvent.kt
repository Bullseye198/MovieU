package com.example.movieu.movie.moviedetail

sealed class MediaDetailEvent {
    data class OnStart(val id: Int, val isSeries: Boolean) : MediaDetailEvent()
}