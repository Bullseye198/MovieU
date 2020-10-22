package com.example.domain.tmdbmovie.usecases.movielist

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.FlowUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.movielist.Result
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import java.util.*
import javax.inject.Inject

class ObserveTMDbMoviesUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : FlowUseCase<List<Result>, ObserveTMDbMoviesUseCase.Params>(appCoroutineDispatchers) {

    @ExperimentalCoroutinesApi
    private val localDatabaseSearchStream: MutableStateFlow<String> = MutableStateFlow("")

    @ExperimentalCoroutinesApi
    override fun buildStream(params: Params?): Flow<List<Result>> {
        return tmDbMovieRepository.observeTMDbMoviesForTitle(params!!.titleToSearchFor)
            .combine(localDatabaseSearchStream) { movies, searchTerm ->
                movies.filter {
                    it.title.toLowerCase(Locale.ROOT).contains(searchTerm.toLowerCase(Locale.ROOT))
                }
                    .sortedWith(compareBy({ it.id }, { it.title }))
            }
    }

    data class Params(
        val titleToSearchFor: String,
        val nameToSearchFor: String
    )
}