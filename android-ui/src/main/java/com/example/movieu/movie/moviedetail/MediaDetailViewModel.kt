package com.example.movieu.movie.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.movie.usecases.RefreshMovieDetailUseCase
import com.example.domain.tmdbmovie.model.UIMediaDetail
import com.example.domain.tmdbmovie.usecases.moviedetail.ObserveTMDbMovieDetailUseCase
import com.example.domain.tmdbmovie.usecases.moviedetail.RefreshTMDbCreditsUseCase
import com.example.domain.tmdbmovie.usecases.moviedetail.RefreshTMDbMovieDetailUseCase
import com.example.domain.tmdbmovie.usecases.tvdetail.ObserveTMDbTvDetailUseCase
import com.example.domain.tmdbmovie.usecases.tvdetail.RefreshTMDbTvDetailUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MediaDetailViewModel @Inject constructor(
    private val observeTMDbMovieDetailUseCase: ObserveTMDbMovieDetailUseCase,
    private val observeTMDbTvDetailUseCase: ObserveTMDbTvDetailUseCase,
    private val refreshMovieDetailUseCase: RefreshMovieDetailUseCase,
    private val refreshTMDbMovieDetailUseCase: RefreshTMDbMovieDetailUseCase,
    private val refreshTMDbCreditsUseCase: RefreshTMDbCreditsUseCase,
    private val refreshTMDbTvDetailUseCase: RefreshTMDbTvDetailUseCase
) : ViewModel() {

    private val mediaDetailState = MutableLiveData(MediaDetailState())
    fun getMediaState(): LiveData<MediaDetailState> = mediaDetailState

    private val currentMovieDetail: String? = null

    private var fetchedOmdbInformation: Boolean = false

    fun handleEvent(event: MediaDetailEvent) {
        when (event) {
            is MediaDetailEvent.OnStart -> {
                if (event.isSeries) {
                    observeTMDbTvDetail(id = event.id)
                    refreshTvDetail(event.id)
                } else {
                    observeMovieDetail(id = event.id)
                    refreshMovieDetail(event.id)
                }
            }
        }
    }

    private fun observeMovieDetail(id: Int) {
        viewModelScope.launch {
            observeTMDbMovieDetailUseCase.buildStream(
                ObserveTMDbMovieDetailUseCase.Params(
                    id
                )
            )
                .catch { }
                .collect { t ->
                    mediaDetailState.value = mediaDetailState.value!!.copy(
                        uiMediaDetail = UIMediaDetail(
                            backdropPath = t.backdropPath,
                            id = t.id,
                            status = t.status,
                            homepage = t.homepage,
                            originalLanguage = t.originalLanguage,
                            popularity = t.popularity,
                            voteAverage = t.voteAverage,
                            voteCount = t.voteCount,
                            overview = t.overview,
                            posterPath = t.posterPath,
                            crew = t.crew,
                            cast = t.cast,
                            belongsToCollection = t.belongsToCollection,
                            imdbID = t.imdbID,
                            imdbRating = t.imdbRating,
                            imdbVotes = t.imdbVotes,
                            title = t.title,
                            budget = t.budget,
                            imdbId = t.imdbID,
                            revenue = t.revenue,
                            runtime = t.runtime,
                            adult = t.adult,
                            tagline = t.tagline,
                            video = t.video,
                            genres = t.genres,
                            originalTitle = t.originalTitle,
                            productionCompanies = t.productionCompanies,
                            productionCountries = t.productionCountries,
                            releaseDate = t.releaseDate,
                            spokenLanguages = t.spokenLanguages,
                            nextEpisodeToAir = null,
                            languages = null,
                            originCountry = null,
                            tvDetailSeasons = null,
                            tvDetailProductionCompanies = null,
                            tvDetailNetworks = null,
                            tvDetailLastEpisodeToAir = null,
                            tvDetailGenres = null,
                            tvDetailCreatedBy = null,
                            inProduction = null,
                            lastAirDate = null,
                            numberOfEpisodes = null,
                            numberOfSeasons = null,
                            type = null,
                            firstAirDate = null,
                            originalName = null,
                            name = null
                        )
                    )
                    if (t.imdbId != null && !fetchedOmdbInformation) {
                        refreshOMDbBaseInformation(t.imdbId!!)
                        fetchedOmdbInformation = true
                    }
                }
        }
    }

    private fun observeTMDbTvDetail(id: Int) {
        viewModelScope.launch {
            observeTMDbTvDetailUseCase.buildStream(
                ObserveTMDbTvDetailUseCase.Params(
                    id
                )
            )
                .catch { }
                .collect { t ->
                    mediaDetailState.value = mediaDetailState.value!!.copy(
                        uiMediaDetail = UIMediaDetail(
                            backdropPath = t.backdropPath,
                            id = t.id,
                            nextEpisodeToAir = t.nextEpisodeToAir,
                            languages = t.languages,
                            originCountry = t.originCountry,
                            tvDetailSeasons = t.tvDetailSeasons,
                            tvDetailProductionCompanies = t.tvDetailProductionCompanies,
                            tvDetailNetworks = t.tvDetailNetworks,
                            tvDetailLastEpisodeToAir = t.tvDetailLastEpisodeToAir,
                            tvDetailGenres = t.tvDetailGenres,
                            tvDetailCreatedBy = t.tvDetailCreatedBy,
                            inProduction = t.inProduction,
                            lastAirDate = t.lastAirDate,
                            numberOfEpisodes = t.numberOfEpisodes,
                            numberOfSeasons = t.numberOfSeasons,
                            status = t.status,
                            homepage = t.homepage,
                            type = t.type,
                            firstAirDate = t.firstAirDate,
                            originalLanguage = t.originalLanguage,
                            originalName = t.originalName,
                            popularity = t.popularity,
                            voteAverage = t.voteAverage,
                            voteCount = t.voteCount,
                            overview = t.overview,
                            posterPath = t.posterPath,
                            name = t.name,
                            crew = null,
                            cast = null,
                            belongsToCollection = null,
                            imdbID = null,
                            imdbRating = null,
                            imdbVotes = null,
                            title = null,
                            budget = null,
                            imdbId = null,
                            revenue = null,
                            runtime = null,
                            adult = null,
                            tagline = null,
                            video = null,
                            genres = null,
                            originalTitle = null,
                            productionCompanies = null,
                            productionCountries = null,
                            releaseDate = null,
                            spokenLanguages = null
                        )
                    )
                }

        }
    }

    private fun refreshMovieDetail(id: Int) {
        viewModelScope.launch {
            currentMovieDetail.let {
                refreshTMDbMovieDetailUseCase.invokeUseCase(
                    params = RefreshTMDbMovieDetailUseCase.Params(id = id)
                )
                refreshTMDbCreditsUseCase.invokeUseCase(
                    params = RefreshTMDbCreditsUseCase.Params(id = id)
                )

            }
        }
    }

    private fun refreshTvDetail(id: Int) {
        viewModelScope.launch {
            currentMovieDetail.let {
                refreshTMDbTvDetailUseCase.invokeUseCase(
                    params = RefreshTMDbTvDetailUseCase.Params(id = id)
                )
            }
        }
    }

    private fun refreshOMDbBaseInformation(imdbID: String) {
        viewModelScope.launch {
            currentMovieDetail.let {
                refreshMovieDetailUseCase.invokeUseCase(
                    params = RefreshMovieDetailUseCase.Params(imdbID)
                )
            }
        }
    }
}
