package com.example.batmanmovies.repository

import com.example.batmanmovies.model.MovieDetail
import com.example.batmanmovies.model.SearchMovie
import com.example.batmanmovies.repository.local.MovieDao
import com.example.batmanmovies.repository.local.MovieDetailDao
import com.example.batmanmovies.repository.remote.ApiService
import com.example.batmanmovies.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao ,
    private val movieDetailDao: MovieDetailDao
) {

    fun getAllMovies(): Flow<Resource<List<SearchMovie>>> = flow {

        emit(Resource.Loading())
        val oldData = movieDao.getAllMoviesFromDB()
        emit(Resource.Loading(oldData))

        try {

            val movieData = apiService.getAllMovies().movieList
            movieDao.deleteAllMovies()
            movieDao.insertAllMovies(movieData)
            val lastData = movieDao.getAllMoviesFromDB()
            emit(Resource.Success(data = lastData))

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "error in fetch"))
        }
    }

    fun getMovieDetail(imdbId:String):Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())
        val oldMovie = movieDetailDao.getMovieDetail(imdbId)
        emit(Resource.Loading(oldMovie))
        try {
            val responseData = apiService.getMovieDetail(imdbId)
            movieDetailDao.deleteMovie(imdbId)
            movieDetailDao.insertMovieDetail(responseData)
            val lastMovie = movieDetailDao.getMovieDetail(imdbId)
            emit(Resource.Success(data = lastMovie))

        }catch (e:Exception)
        {
            emit(Resource.Error(e.localizedMessage?:"error in fetch or Db"))
        }
    }

}