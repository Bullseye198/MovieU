package com.example.movieu.dependencyInjection

import com.example.movieu.movie.moviedetail.MediaDetailFragment
import com.example.movieu.movie.medialist.MediaListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MovieUIModule {

    @ContributesAndroidInjector
    fun contributesMovieListFragment(): MediaListFragment

    @ContributesAndroidInjector
    fun contributesMovieDetailFragment(): MediaDetailFragment
}