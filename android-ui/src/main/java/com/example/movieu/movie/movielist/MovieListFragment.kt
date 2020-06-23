package com.example.movieu.movie.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieu.databinding.FragmentMovieListBinding
import com.example.movieu.dependencyInjection.ViewModelFactory
import com.example.movieu.movie.media.MediaListAdapter
import com.example.movieu.movie.media.MediaListEvent
import com.example.movieu.movie.media.MediaListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieListFragment : DaggerFragment() {

    private lateinit var viewModel: MediaListViewModel
    private lateinit var adapter: MediaListAdapter

    //private lateinit var viewModel: MovieListViewModel
    //private lateinit var adapter: MovieListAdapter
    private lateinit var binding: FragmentMovieListBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setUpMediaListAdapter()
        onMovieSearched()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MediaListViewModel::class.java)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.recListFragment.adapter = null
    }

    private fun onMovieSearched() {
        binding.searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onNewMediaSearched(newMedia = query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onNewMediaSearched(newMedia = newText.toString())
                return true
            }
        })
    }

    private fun setUpMediaListAdapter() {
        adapter = MediaListAdapter()
        binding.recListFragment.adapter = adapter
        binding.recListFragment.layoutManager = GridLayoutManager(requireContext(), 3)

        adapter.event.observe(
            viewLifecycleOwner, Observer {
                if (it is MediaListEvent.OnMediaItemClick) {
                    val direction =
                        MovieListFragmentDirections.actionMovieListFragmentToMovieDetail(it.mediaId, it.isSeries)
                    findNavController().navigate(direction)
                }
            }
        )
    }

    private fun observeViewModel() {
        viewModel.getState().observe(
            viewLifecycleOwner,
            Observer { mediaListState ->
                if (mediaListState != null) {
                    adapter.submitList(mediaListState.feed)
                }
            }
        )
    }
}

