package com.example.movieu.movie.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.movie.usecases.RefreshMovieDetailUseCase
import com.example.domain.tmdbmovie.model.UIMediaDetail
import com.example.domain.tmdbmovie.model.moviedetail.TMDbMovieDetail
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail
import com.example.domain.tmdbmovie.usecases.moviedetail.RefreshTMDbCreditsUseCase
import com.example.domain.tmdbmovie.usecases.moviedetail.ObserveTMDbMovieDetailUseCase
import com.example.domain.tmdbmovie.usecases.moviedetail.RefreshTMDbMovieDetailUseCase
import com.example.domain.tmdbmovie.usecases.tvdetail.ObserveTMDbTvDetailUseCase
import com.example.domain.tmdbmovie.usecases.tvdetail.RefreshTMDbTvDetailUseCase
import com.example.movieu.movie.tvdetail.MediaDetailState
import io.reactivex.subscribers.DisposableSubscriber
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

    val currentMovieDetail: String? = null

    var fetchedOmdbInformation: Boolean = false

    fun handleEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.OnStart -> {
                if(event.isSeries){
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
        observeTMDbMovieDetailUseCase.invokeUseCase(
            object : DisposableSubscriber<TMDbMovieDetail>() {
                override fun onComplete() {

                }

                override fun onNext(t: TMDbMovieDetail?) {
                    mediaDetailState.value = mediaDetailState.value!!.copy(
                        uiMediaDetail = UIMediaDetail(
                            backdropPath = t?.backdropPath,
                            id = t?.id,
                            status = t?.status,
                            homepage = t?.homepage,
                            originalLanguage = t?.originalLanguage,
                            popularity = t?.popularity,
                            voteAverage = t?.voteAverage,
                            voteCount = t?.voteCount,
                            overview = t?.overview,
                            posterPath = t?.posterPath,
                            crew = t?.crew,
                            cast = t?.cast,
                            belongsToCollection = t?.belongsToCollection,
                            imdbID = t?.imdbID,
                            imdbRating = t?.imdbRating,
                            imdbVotes = t?.imdbVotes,
                            title = t?.title,
                            budget = t?.budget,
                            imdbId = t?.imdbID,
                            revenue = t?.revenue,
                            runtime = t?.runtime,
                            adult = t?.adult,
                            tagline = t?.tagline,
                            video = t?.video,
                            genres = t?.genres,
                            originalTitle = t?.originalTitle,
                            productionCompanies = t?.productionCompanies,
                            productionCountries = t?.productionCountries,
                            releaseDate = t?.releaseDate,
                            spokenLanguages = t?.spokenLanguages,
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
                    if (t?.imdbId != null && !fetchedOmdbInformation) {
                        refreshOMDbBaseInformation(t.imdbId!!)
                        fetchedOmdbInformation = true
                    }
                }

                override fun onError(t: Throwable?) {
                    throw Exception("Subscription failed at ${t?.localizedMessage}")
                }
            }, ObserveTMDbMovieDetailUseCase.Params(id)
        )
    }

    private fun observeTMDbTvDetail(id: Int) {
        observeTMDbTvDetailUseCase.invokeUseCase(
            object : DisposableSubscriber<TMDbTvDetail>() {
                override fun onComplete() {
                }

                override fun onNext(t: TMDbTvDetail?) {
                    mediaDetailState.value = mediaDetailState.value!!.copy(
                        uiMediaDetail = UIMediaDetail(
                            backdropPath = t?.backdropPath,
                            id = t?.id,
                            nextEpisodeToAir = t?.nextEpisodeToAir,
                            languages = t?.languages,
                            originCountry = t?.originCountry,
                            tvDetailSeasons = t?.tvDetailSeasons,
                            tvDetailProductionCompanies = t?.tvDetailProductionCompanies,
                            tvDetailNetworks = t?.tvDetailNetworks,
                            tvDetailLastEpisodeToAir = t?.tvDetailLastEpisodeToAir,
                            tvDetailGenres = t?.tvDetailGenres,
                            tvDetailCreatedBy = t?.tvDetailCreatedBy,
                            inProduction = t?.inProduction,
                            lastAirDate = t?.lastAirDate,
                            numberOfEpisodes = t?.numberOfEpisodes,
                            numberOfSeasons = t?.numberOfSeasons,
                            status = t?.status,
                            homepage = t?.homepage,
                            type = t?.type,
                            firstAirDate = t?.firstAirDate,
                            originalLanguage = t?.originalLanguage,
                            originalName = t?.originalName,
                            popularity = t?.popularity,
                            voteAverage = t?.voteAverage,
                            voteCount = t?.voteCount,
                            overview = t?.overview,
                            posterPath = t?.posterPath,
                            name = t?.name,
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

                override fun onError(t: Throwable?) {
                    throw Exception("Subscription failed at ${t?.localizedMessage}")
                }
            }, ObserveTMDbTvDetailUseCase.Params(id)
        )
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
