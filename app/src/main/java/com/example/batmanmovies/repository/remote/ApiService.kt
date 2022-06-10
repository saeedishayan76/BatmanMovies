package com.example.batmanmovies.repository.remote

import com.example.batmanmovies.model.MovieDetail
import com.example.batmanmovies.model.MovieDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("?apikey=3e974fca")
    suspend fun getAllMovies(
        @Query("s") searchKey: String? = "batman"
    ): MovieDto

    @GET("?apikey=3e974fca")
    suspend fun getMovieDetail(
        @Query("i")movieId:String
    ):MovieDetail

}