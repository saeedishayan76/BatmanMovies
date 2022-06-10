package com.example.batmanmovies.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.batmanmovies.model.MovieDetail

@Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movieDetail: MovieDetail)

    @Query("SELECT * FROM MovieDetail WHERE imdbID=:imdbId")
    suspend fun getMovieDetail(imdbId:String):MovieDetail

    @Query("DELETE FROM MovieDetail WHERE imdbID=:imdbId")
    suspend fun deleteMovie(imdbId: String)
}