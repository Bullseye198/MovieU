package com.example.movieu.movie.movielist

sealed class MovieListEvent {
    data class OnMovieItemClick(val position: Int, val movieId: Int) : MovieListEvent()

}