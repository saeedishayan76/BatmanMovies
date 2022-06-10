package com.example.batmanmovies.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.batmanmovies.databinding.MovieItemBinding
import com.example.batmanmovies.model.SearchMovie

class MovieAdapter(val context:Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

     var callBack:CallBack ?= null


    var movies = arrayListOf<SearchMovie>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(movie: SearchMovie) {

            Glide.with(context)
                .load(movie.Poster)
                .into(binding.imgMovie)
            binding.tvNameMovie.text = movie.Title
            binding.tvMovieYear.text = movie.Year

            itemView.setOnClickListener {
                callBack?.ItemClicked(adapterPosition,movie.imdbID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    interface CallBack{
        fun ItemClicked(pos:Int , id:String)
    }
}