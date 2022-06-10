package com.example.batmanmovies.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.batmanmovies.model.MovieDetail
import com.example.batmanmovies.model.SearchMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovies(movieList: List<SearchMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie:MovieDetail)
    @Query("SELECT * FROM SearchMovie")
    suspend fun getAllMoviesFromDB(): List<SearchMovie>

    @Query("DELETE FROM SearchMovie")
    suspend fun deleteAllMovies()

}