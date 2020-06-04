package com.example.movieu.movie.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.movie.model.Movie
import com.example.movieu.R
import com.example.movieu.dependencyInjection.ViewModelFactory
import com.example.movieu.movie.MovieListViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

class MovieListFragment : DaggerFragment() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter: MovieListAdapter


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setUpMovieListAdapter()
        onMovieSearched()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        rec_list_fragment.adapter = null
    }

    private fun onMovieSearched() {
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //viewModel.onMovieSearched(titleToSearchFor = query.toString())
                viewModel.onNewMoviesSearched(newMovies = query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onNewMoviesSearched(newMovies = newText.toString())
                return true
            }
        })
    }

    private fun setUpMovieListAdapter() {
        adapter = MovieListAdapter()
        rec_list_fragment.adapter = adapter
        rec_list_fragment.layoutManager = GridLayoutManager(requireContext(), 3)

        adapter.event.observe(
            viewLifecycleOwner, Observer {
                if (it is MovieListEvent.OnMovieItemClick) {
                    val direction =
                        MovieListFragmentDirections.actionMovieListFragmentToMovieDetail(it.movieId)
                    findNavController().navigate(direction)
                }
            }
        )
    }

    private fun observeViewModel() {
        viewModel.getState().observe(
            viewLifecycleOwner,
            Observer { t ->
                if (t != null) {
                    adapter.submitList(t.feed)
                }
            }
        )
    }
}

