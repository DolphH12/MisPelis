package com.dolphhincapie.mispelis.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.dolphhincapie.mispelis.R
import com.dolphhincapie.mispelis.model.remote.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MoviesAdapter(val moviesList: ArrayList<Movie>):
        RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item,parent,false)
        return MoviesViewHolder(itemView)
    }

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = moviesList[position]
        holder.setMovie(movie)
    }


    class MoviesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun setMovie(movie: Movie){
            val URL_IMAGES = "https://image.tmdb.org/t/p/w500"
            itemView.tv_title.text = movie.title
            itemView.tv_average.text = movie.voteAverage.toString()
            Picasso.get().load(URL_IMAGES + movie.posterPath).into(itemView.iv_poster)
        }

    }
}