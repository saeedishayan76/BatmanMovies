package com.example.batmanmovies.di

import android.content.Context
import androidx.room.Room
import com.example.batmanmovies.App
import com.example.batmanmovies.repository.local.AppDataBase
import com.example.batmanmovies.repository.local.MovieDao
import com.example.batmanmovies.repository.local.MovieDetailDao
import com.example.batmanmovies.repository.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext app: Context): AppDataBase {
        return Room.databaseBuilder(app, AppDataBase::class.java, "db_movie")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideMovieDao(appDataBase: AppDataBase): MovieDao {
        return appDataBase.moviesData()
    }

    @Provides
    fun provideMovieDetailDao(appDataBase: AppDataBase): MovieDetailDao {
        return appDataBase.movieDetailDao()
    }
}