package com.example.batmanmovies.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.batmanmovies.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) :ViewModel() {

    private val movieIDLiveData = MutableLiveData<String>()



    fun getAllMovies() = movieRepository.getAllMovies().asLiveData()
    fun getMovieDetail(imdbId:String) = movieRepository.getMovieDetail(imdbId).asLiveData()


    fun setMovieID(imdbId:String) {
        this.movieIDLiveData.value = imdbId
    }

    fun getMovieId() = this.movieIDLiveData
}