package com.example.domain.tmdbmovie.model.tvdetail

data class TMDbTvDetail(
    val backdropPath: String, // /1qVOS9wcChP1Bt4Dnnxrb25aSJa.jpg
    val createdBy: List<CreatedBy>,
    val episodeRunTime: List<Int>,
    val firstAirDate: String, // 2009-09-10
    val genres: List<Genre>,
    val homepage: String, // http://www.cwtv.com/shows/the-vampire-diaries
    val id: Int, // 18165
    val inProduction: Boolean, // false
    val languages: List<String>,
    val lastAirDate: String, // 2017-03-10
    val lastEpisodeToAir: LastEpisodeToAir,
    val name: String, // The Vampire Diaries
    val networks: List<Network>,
    val nextEpisodeToAir: Any, // null
    val numberOfEpisodes: Int, // 171
    val numberOfSeasons: Int, // 8
    val originCountry: List<String>,
    val originalLanguage: String, // en
    val originalName: String, // The Vampire Diaries
    val overview: String, // The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.
    val popularity: Double, // 65.142
    val posterPath: String, // /aBkVgChtyyJaHyZh1gfd8DbzQon.jpg
    val productionCompanies: List<ProductionCompany>,
    val seasons: List<Season>,
    val status: String, // Ended
    val type: String, // Scripted
    val voteAverage: Double, // 8.1
    val voteCount: Int // 2145
)