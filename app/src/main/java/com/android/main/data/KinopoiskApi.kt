package com.example.main.data

import com.android.main.data.ItemsResponse
import com.android.main.data.movie.TopMoviesResponse
import com.android.main.data.image.GalleryImageDto
import com.android.main.data.movie.MovieShortDto
import com.android.main.data.movie.MovieDto
import com.android.main.data.movie.MovieSeriesSeasonDto
import com.android.main.data.movie.MovieType
import com.android.main.data.movie.TopMoviesType
import com.android.main.data.staff.StaffDto
import com.android.main.data.staff.StaffShortDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskApi {
    @GET("v2.2/films/{id}")
    suspend fun getMovieById(@Path("id") id: Int): MovieDto

    @GET("v2.2/films/premieres?year=2023&month=JUNE")
    suspend fun getPremieres(): ItemsResponse<MovieShortDto>

    @GET("v2.2/films/top")
    suspend fun getTopList(@Query("type") type: TopMoviesType, @Query("page") page: Int): TopMoviesResponse

    @GET("v2.2/films/{id}/similars")
    suspend fun getSimilarMovies(@Path("id") id: Int): ItemsResponse<MovieShortDto>

    @GET("v1/staff")
    suspend fun getStaffByMovieID(@Query("filmId") movieId: Int): List<StaffShortDto>

    @GET("v1/staff/{id}")
    suspend fun getStaffByID(@Path("id") staffId: Int): StaffDto

    @GET("v2.2/films/{id}/images")
    suspend fun getImages(@Path("id") id: Int, @Query("page") page: Int): ItemsResponse<GalleryImageDto>

    @GET("v2.2/films/{id}/seasons")
    suspend fun getSeasons(@Path("id") id: Int): ItemsResponse<MovieSeriesSeasonDto>

    @GET("v2.2/films")
    suspend fun getMoviesByFilters(
        @Query("countries") countries: Int?,
        @Query("genres") genres: Int?,
        @Query("type") type: MovieType?,
        @Query("ratingFrom") ratingFrom: Int?,
        @Query("ratingTo") ratingTo: Int?,
        @Query("yearFrom") yearFrom: Int?,
        @Query("yearTo") yearTo: Int?,
        @Query("imdbId") imdbId: String?,
        @Query("keyword") keyword: String?,
        @Query("page") page: Int?,
    ): ItemsResponse<MovieShortDto>
}