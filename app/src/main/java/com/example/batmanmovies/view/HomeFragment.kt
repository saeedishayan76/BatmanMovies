package com.example.batmanmovies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.batmanmovies.R
import com.example.batmanmovies.databinding.FragmentHomeBinding
import com.example.batmanmovies.model.SearchMovie
import com.example.batmanmovies.view.adapter.MovieAdapter
import com.example.batmanmovies.viewModel.MovieViewModel
import com.example.batmanmovies.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val movieViewModel by activityViewModels<MovieViewModel>()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var movieAdapter:MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPage()
        fetchMovies()
    }

    private fun setUpPage() {
         movieAdapter = MovieAdapter(requireContext())
        binding.recMovies.apply {
            adapter = movieAdapter
        }
        movieAdapter.callBack = object : MovieAdapter.CallBack {
            override fun ItemClicked(pos: Int, id: String) {
                Timber.d("item is $pos $id")
                movieViewModel.setMovieID(id)
                findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
            }

        }
    }

    private fun fetchMovies() {
        movieViewModel.getAllMovies().observe(this)
        {
            when (it) {
                is Resource.Loading -> {

                    Timber.d("in home loading ${it.data}")
                    if (!it.data.isNullOrEmpty())
                    {
                        addData(it.data)
                    }
                }
                is Resource.Success -> {
                    Timber.d("in home Success ${it.data}")
                    addData(it.data)
                }
                is Resource.Error -> {
                    Timber.d("in home error ${it.message}")
                }
            }
        }
    }

    private fun addData(movieList: List<SearchMovie>) {

        movieAdapter.movies = movieList as ArrayList<SearchMovie>


    }

}