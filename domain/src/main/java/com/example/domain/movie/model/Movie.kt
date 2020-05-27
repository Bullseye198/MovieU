package com.example.domain.movie.model

data class Movie(
   var imdbID: String,
   val poster: String,
   val title: String,
   val type: String,
   val year: String
)