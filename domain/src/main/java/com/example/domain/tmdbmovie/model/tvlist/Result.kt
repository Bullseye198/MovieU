package com.example.domain.tmdbmovie.model.tvlist

data class Result(
    val backdropPath: String, // /1qVOS9wcChP1Bt4Dnnxrb25aSJa.jpg
    val firstAirDate: String, // 2009-09-10
    val genreIds: List<Int>,
    val id: Int, // 18165
    val name: String, // The Vampire Diaries
    val originCountry: List<String>,
    val originalLanguage: String, // en
    val originalName: String, // The Vampire Diaries
    val overview: String, // The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.
    val popularity: Double, // 65.142
    val posterPath: String, // /aBkVgChtyyJaHyZh1gfd8DbzQon.jpg
    val voteAverage: Double, // 8.1
    val voteCount: Int // 2139
)