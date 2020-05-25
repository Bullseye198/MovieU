package com.example.data.movie

import com.example.domain.movie.IMovieRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepoImpl @Inject constructor(
    private val movieRemote: MovieRemote
) : IMovieRepository {


}