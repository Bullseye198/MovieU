package com.example.domain.tmdbmovie.usecases.tvlist

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.FlowUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.tvlist.TvListResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import java.util.*
import javax.inject.Inject

class ObserveTMDbTvListUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : FlowUseCase<List<TvListResult>, ObserveTMDbTvListUseCase.Params>(appCoroutineDispatchers) {

    @ExperimentalCoroutinesApi
    private val localDatabaseSearchStream: MutableStateFlow<String> = MutableStateFlow("")

    override fun buildStream(params: Params?): Flow<List<TvListResult>> {
        return tmDbMovieRepository.observeTMDbTvListForTitle(params!!.nameToSearchFor)
            .combine(localDatabaseSearchStream) { tv, searchTerm ->
                tv.filter {
                    it.name.toLowerCase(Locale.ROOT).contains(searchTerm.toLowerCase(Locale.ROOT))
                }
                    .sortedWith(compareBy({ it.id }, { it.name }))
            }
    }

    data class Params(
        val titleToSearchFor: String,
        val nameToSearchFor: String
    )
}