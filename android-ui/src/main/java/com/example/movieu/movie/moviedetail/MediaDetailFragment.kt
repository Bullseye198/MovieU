package com.example.movieu.movie.moviedetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.movieu.databinding.FragmentMovieDetailBinding
import com.example.movieu.dependencyInjection.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MediaDetailFragment : DaggerFragment() {

    private lateinit var viewModel: MediaDetailViewModel
    private lateinit var genreAdapter: MovieGenreAdapter
    private lateinit var castAdapter: MovieCastAdapter
    private lateinit var crewAdapter: MovieCrewAdapter
    private lateinit var binding: FragmentMovieDetailBinding

    var fetchedMovieDetailInformation: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var imdbID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imdbID = MediaDetailFragmentArgs.fromBundle(requireArguments()).movieID
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MediaDetailViewModel::class.java)
        viewModel.handleEvent(MovieDetailEvent.OnStart(imdbID))
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recListGenre.adapter = null
    }

    override fun onStart() {
        super.onStart()

        observeViewModel()
        setUPMovieGenreAdapter()
        setUpMovieCastAdapter()
        setUpMovieCrewAdapter()
    }


    private fun setUPMovieGenreAdapter() {
        genreAdapter = MovieGenreAdapter()
        binding.recListGenre.adapter = genreAdapter

        viewModel.getMediaState().observe(
            viewLifecycleOwner,
            Observer { movieDetailState ->
                if (movieDetailState != null) {
                    genreAdapter.submitList(movieDetailState.uiMediaDetail?.genres)
                }
            }
        )
    }

    private fun setUpMovieCastAdapter() {
        castAdapter = MovieCastAdapter()
        binding.recListCast.adapter = castAdapter

        viewModel.getMediaState().observe(
            viewLifecycleOwner,
            Observer { movieDetailState ->
                if (movieDetailState != null) {
                    castAdapter.submitList(movieDetailState.uiMediaDetail?.cast)
                }
            }
        )
    }

    private fun setUpMovieCrewAdapter() {
        crewAdapter = MovieCrewAdapter()
        binding.recListCrew.adapter = crewAdapter

        viewModel.getMediaState().observe(
            viewLifecycleOwner,
            Observer { movieDetailState ->
                if (movieDetailState != null) {
                    crewAdapter.submitList(movieDetailState.uiMediaDetail?.crew)
                }
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.getMediaState().observe(
            viewLifecycleOwner,
            Observer { t ->
                val posterPath =
                    "http://image.tmdb.org/t/p/w500/${t.uiMediaDetail?.posterPath}"
                binding.movieDetailView.load(posterPath)

                val backdropPath =
                    "https://image.tmdb.org/t/p/w780/${t.uiMediaDetail?.backdropPath}"

                binding.movieDetailBackdrop.load(backdropPath)

                if (t.uiMediaDetail?.title != null) {
                    binding.lblMovieTitle.text = t.uiMediaDetail?.title

                } else {
                    binding.lblMovieTitle.text = t.uiMediaDetail?.name

                }

                if (t.uiMediaDetail?.releaseDate != null) {
                    binding.lblMovieYear.text = t.uiMediaDetail?.releaseDate

                } else {
                    binding.lblMovieYear.text = t.uiMediaDetail?.firstAirDate

                }

                if (t.uiMediaDetail?.runtime != null) {
                    binding.lblMovieRuntime.text =
                        t.uiMediaDetail.runtime.toString() + " Min"
                } else if (t.uiMediaDetail?.numberOfSeasons != null) {
                    binding.lblMovieRuntime.text =
                        t.uiMediaDetail.numberOfSeasons.toString() + " Seasons"

                } else {
                    binding.lblMovieRuntime.text = "0 Min"
                }

                binding.lblMoviePlot.text = t.uiMediaDetail?.overview
                binding.lblMovieGenre.text = "Genre: "
                binding.lblMovieLanguage.text =
                    "Language: " + t.uiMediaDetail?.originalLanguage

                if (t.uiMediaDetail?.budget == null) {
                    binding.lblMovieBudget.text = "Budget: Unknown"
                } else {
                    binding.lblMovieBudget.text =
                        "Budget: " + t.uiMediaDetail.budget + " Dollars"
                }

                if (t.uiMediaDetail?.imdbId != null) {
                    binding.lblMovieImdbRating.text =
                        "IMDb Rating: " + t.uiMediaDetail.imdbRating
                } else if (t.uiMediaDetail?.voteAverage != null) {
                    binding.lblMovieImdbRating.text = "Vote Average: " + t.uiMediaDetail.voteAverage.toString()
                } else {

                    binding.lblMovieImdbRating.text = "IMDb Rating: Unknown"

                }

                binding.lblMoviePopularity.text = "Popularity: " + t.uiMediaDetail?.popularity
            })
    }
}