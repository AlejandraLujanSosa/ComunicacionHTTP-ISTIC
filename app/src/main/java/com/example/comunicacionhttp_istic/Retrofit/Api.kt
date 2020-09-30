package com.example.comunicacionhttp_istic.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "20c4e1b163a6b9006a45097c238cc785",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "20c4e1b163a6b9006a45097c238cc785",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") apiKey: String = "20c4e1b163a6b9006a45097c238cc785",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}