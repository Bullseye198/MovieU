package com.example.movieu.movie.moviedetail

sealed class MovieDetailEvent {
    data class OnStart(val id: Int, val isSeries: Boolean) : MovieDetailEvent()
}