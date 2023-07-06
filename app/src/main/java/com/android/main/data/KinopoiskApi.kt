package com.example.main.data

import com.android.main.data.movie.MovieDto
import com.android.main.data.movie.MovieListResponse
import com.android.main.data.staff.StaffDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskApi {
    @GET("v2.2/films/{id}")
    suspend fun getMovieById(@Path("id") id: Int): MovieDto

    @GET("v2.2/films/premieres?year=2023&month=JUNE")
    suspend fun getPremieres(): MovieListResponse

    @GET("v1/staff/")
    suspend fun getStaffByMovieID(@Query("filmId") movieId: Int): List<StaffDto>
}