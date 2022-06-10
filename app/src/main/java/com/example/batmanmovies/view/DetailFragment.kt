package com.example.batmanmovies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.batmanmovies.R
import com.example.batmanmovies.common.Util
import com.example.batmanmovies.databinding.FragmentDetailBinding
import com.example.batmanmovies.model.MovieDetail
import com.example.batmanmovies.viewModel.MovieViewModel
import com.example.batmanmovies.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val movieViewModel by activityViewModels<MovieViewModel>()
    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel.getMovieId().observe(viewLifecycleOwner) {
            Timber.d("Selected Id id $it")
            fetchMovieDetail(it)
        }
    }

    private fun fetchMovieDetail(id: String) {
        movieViewModel.getMovieDetail(id).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    Timber.d("movieDetail Loading ${it.data}")
                    if (it.data != null) {
                        prepareData(it.data!!)
                    }
                }
                is Resource.Success -> {
                    Timber.d("movieDetail Success ${it.data}")
                    prepareData(it.data)

                }
                is Resource.Error -> {
                    Timber.d("movieDetail Error ${it.message}")

                }
            }
        }
    }

    private fun prepareData(movieDetail: MovieDetail) {
        Glide.with(requireContext())
            .load(movieDetail.Poster).into(binding.imgMovieDetail)


        Timber.d("rate is ${movieDetail.imdbRating.toFloat() / 2}")

        val orgTime = Util.convertMovieRunTimeToHourMinutes(movieDetail.Runtime)
        Timber.d("org time is $orgTime")
        binding.tvMovieTitle.text = movieDetail.Title
        binding.tvMovieYear.text = "${movieDetail.Year} ."
        binding.tvMovieGenre.text = "${movieDetail.Genre} ."
        binding.tvMovieTime.text = "$orgTime ."


        binding.ratingBar.rating = movieDetail.imdbRating.toFloat() / 2

        binding.tvPlutData.text = movieDetail.Plot
        binding.tvActorsData.text = movieDetail.Actors

        binding.tvWriterData.text = movieDetail.Writer
        binding.tvDirectorData.text = movieDetail.Director


    }




}