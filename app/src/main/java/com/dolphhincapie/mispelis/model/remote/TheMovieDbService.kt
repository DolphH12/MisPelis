package com.dolphhincapie.mispelis.model.remote


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDbService {

    @GET("movie/top_rated")
    fun getTopRated(@Query("api_key") apikey: String): Call<Movies>

    companion object{
        val urlAPI = "https://api.themoviedb.org/3/"

        fun create():TheMovieDbService{

            val interceptor = HttpLoggingInterceptor().run {
                level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder().addInterceptor(this).build()
            }

            val retrofit= Retrofit.Builder()
                .baseUrl(urlAPI)
                .addConverterFactory(GsonConverterFactory.create())
                .client(interceptor)
                .build()

            return retrofit.create(TheMovieDbService::class.java)
        }

    }

}