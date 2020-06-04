package com.example.movieu.movie.moviedetail

sealed class MovieDetailEvent {
    data class OnStart(val imdbID: String) : MovieDetailEvent()
}