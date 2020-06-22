package com.example.movieu.movie.media

import com.example.movieu.movie.moviedetail.MovieDetailEvent

sealed class MediaDetailEvent {
    data class OnStart(val id: Int) : MediaDetailEvent()

}