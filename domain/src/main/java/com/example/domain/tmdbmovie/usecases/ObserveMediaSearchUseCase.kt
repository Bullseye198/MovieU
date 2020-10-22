package com.example.domain.tmdbmovie.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.FlowUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.MediaList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import java.util.*
import javax.inject.Inject
import kotlin.math.min

class ObserveMediaSearchUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : FlowUseCase<List<MediaList>, ObserveMediaSearchUseCase.Params>(appCoroutineDispatchers) {

    @ExperimentalCoroutinesApi
    private val localDatabaseSearchStream: MutableStateFlow<String> = MutableStateFlow("")

    @ExperimentalCoroutinesApi
    override fun buildStream(params: Params?): Flow<List<MediaList>> {
        return combine(
            tmDbMovieRepository.observeTMDbTvListForTitle("%${params!!.mediaToSearchFor}%"),
            tmDbMovieRepository.observeTMDbMoviesForTitle("%${params.mediaToSearchFor}%"),
            localDatabaseSearchStream
        ) { series, movies, searchTerm ->
            val mappedMovies = movies
                .filter { movie ->
                    movie.title.toLowerCase(Locale.ROOT).contains(
                        searchTerm.toLowerCase(
                            Locale.ROOT
                        )
                    )
                }
                .sortedWith(compareBy({ it.id }, { it.title }))
                .map { movie ->
                    MediaList(
                        movie.posterPath,
                        movie.id,
                        movie.title,
                        movie.releaseDate,
                        false
                    )
                }
            val mappedSeries = series.filter { tvSeries ->
                tvSeries.name.toLowerCase(Locale.ROOT).contains(
                    searchTerm.toLowerCase(
                        Locale.ROOT
                    )
                )
            }
                .sortedWith(compareBy({ it.id }, { it.name }))
                .map {
                    MediaList(
                        it.posterPath,
                        it.id,
                        it.name,
                        it.firstAirDate,
                        true
                    )
                }
            val list = mappedMovies + mappedSeries
            list.subList(0, min(20, list.size))
        }
    }

@ExperimentalCoroutinesApi
fun onSearchTermChanged(newSearchTerm: String) {
    localDatabaseSearchStream.value = newSearchTerm
}

data class Params(
    val mediaToSearchFor: String
)
}