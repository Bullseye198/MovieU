package com.example.data.movie

import com.example.domain.movie.model.Movie

interface MovieRemote {

    suspend fun fetchImages(): List<Movie>
}