package com.example.movieu.dependencyInjection

import com.example.movieu.movie.moviedetail.MediaDetailFragment
import com.example.movieu.movie.movielist.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MovieUIModule {

    @ContributesAndroidInjector
    fun contributesMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    fun contributesMovieDetailFragment(): MediaDetailFragment
}