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

        viewModel.getMovieState().observe(
            viewLifecycleOwner,
            Observer { movieDetailState ->
                if (movieDetailState != null) {
                    genreAdapter.submitList(movieDetailState.tmDbMovieDetail?.genres)
                }
            }
        )
    }

    private fun setUpMovieCastAdapter() {
        castAdapter = MovieCastAdapter()
        binding.recListCast.adapter = castAdapter

        viewModel.getMovieState().observe(
            viewLifecycleOwner,
            Observer { movieDetailState ->
                if (movieDetailState != null) {
                    castAdapter.submitList(movieDetailState.tmDbMovieDetail?.cast)
                }
            }
        )
    }

    private fun setUpMovieCrewAdapter() {
        crewAdapter = MovieCrewAdapter()
        binding.recListCrew.adapter = crewAdapter

        viewModel.getMovieState().observe(
            viewLifecycleOwner,
            Observer { movieDetailState ->
                if (movieDetailState != null) {
                    crewAdapter.submitList(movieDetailState.tmDbMovieDetail?.crew)
                }
            }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.getMovieState().observe(
            viewLifecycleOwner,
            Observer { t ->
                if (t != null) {
                    val posterPath =
                        "http://image.tmdb.org/t/p/w500/${t.tmDbMovieDetail?.posterPath}"
                    binding.movieDetailView.load(posterPath)
                    val backdropPath =
                        "https://image.tmdb.org/t/p/w780/${t.tmDbMovieDetail?.backdropPath}"
                    binding.movieDetailBackdrop.load(backdropPath)
                    binding.lblMovieTitle.text = t.tmDbMovieDetail?.title
                    binding.lblMovieYear.text = t.tmDbMovieDetail?.releaseDate

                    if (t.tmDbMovieDetail?.runtime == null) {
                        binding.lblMovieRuntime.text = ""
                    } else {
                        binding.lblMovieRuntime.text =
                            t.tmDbMovieDetail?.runtime.toString() + " Min"
                    }

                    binding.lblMoviePlot.text = t.tmDbMovieDetail?.overview
                    binding.lblMovieGenre.text = "Genre: "
                    binding.lblMovieLanguage.text =
                        "Language: " + t.tmDbMovieDetail?.originalLanguage

                    if (t.tmDbMovieDetail?.budget == null) {
                        binding.lblMovieBudget.text = "Budget: Unknown"
                    } else {
                        binding.lblMovieBudget.text =
                            "Budget: " + t.tmDbMovieDetail?.budget + " Dollars"
                    }

                    if (t.tmDbMovieDetail?.imdbId == null) {
                        binding.lblMovieImdbRating.text = "IMDb Rating: Unknown"
                    } else {
                        binding.lblMovieImdbRating.text =
                            "IMDb Rating: " + t.tmDbMovieDetail?.imdbRating
                    }

                    binding.lblMoviePopularity.text = "Popularity: " + t.tmDbMovieDetail?.popularity
                }
            }
        )
    }
}