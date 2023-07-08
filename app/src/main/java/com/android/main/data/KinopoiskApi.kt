package com.example.main.data

import com.android.main.data.ItemsResponse
import com.android.main.data.image.GalleryImageDto
import com.android.main.data.movie.MovieShortDto
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
    suspend fun getPremieres(): ItemsResponse<MovieShortDto>

    @GET("v2.2/films/{id}/similars")
    suspend fun getSimilarMovies(@Path("id") id: Int): ItemsResponse<MovieShortDto>

    @GET("v1/staff/")
    suspend fun getStaffByMovieID(@Query("filmId") movieId: Int): List<StaffDto>

    @GET("v2.2/films/{id}/images")
    suspend fun getImages(@Path("id") id: Int, @Query("page") page: Int): ItemsResponse<GalleryImageDto>
}