package com.dolphhincapie.mispelis.ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dolphhincapie.mispelis.R
import com.dolphhincapie.mispelis.model.remote.Movie
import com.dolphhincapie.mispelis.model.remote.Movies
import com.dolphhincapie.mispelis.model.remote.TheMovieDbService
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    private var listMovies = ArrayList<Movie>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_list, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadList()

        rv_List.setHasFixedSize(true)
        rv_List.layoutManager = LinearLayoutManager(
            requireContext(),RecyclerView.VERTICAL,false
        )

    }

    private fun loadList(){
        val apiKey = "210b40032561245c4d44bd5a86f06c0c"

        TheMovieDbService.create()
            .getTopRated(apiKey)
            .enqueue(object : Callback<Movies>{
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    Log.d("Error", t.message!!)
                }

                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    if (response.isSuccessful){
                        listMovies = response.body()?.results as ArrayList<Movie>
                        rv_List.adapter = MoviesAdapter(listMovies)

                    }
                }

            })
    }

}