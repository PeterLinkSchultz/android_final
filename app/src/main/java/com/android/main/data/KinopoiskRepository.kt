package com.example.main.data

import com.android.main.data.ItemsResponse
import com.android.main.data.image.GalleryImageDto
import com.android.main.data.movie.MovieShortDto
import com.android.main.data.movie.MovieDto
import com.android.main.data.movie.MovieFilterParams
import com.android.main.data.movie.MovieSeriesSeasonDto
import com.android.main.data.movie.TopMoviesResponse
import com.android.main.data.movie.TopMoviesType
import com.android.main.data.staff.StaffDto
import com.android.main.data.staff.StaffShortDto
import javax.inject.Inject

class KinopoiskRepository @Inject constructor(){
    suspend fun getPremieres(): ItemsResponse<MovieShortDto> {
        return Network.kinopoiskApi.getPremieres()
    }
    suspend fun getMovie(movieId: Int): MovieDto {
        return Network.kinopoiskApi.getMovieById(movieId)
    }
    suspend fun getStaffByMovieId(movieId: Int): List<StaffShortDto> {
        return Network.kinopoiskApi.getStaffByMovieID(movieId)
    }

    suspend fun getMovieImages(movieId: Int, page: Int): ItemsResponse<GalleryImageDto> {
        return Network.kinopoiskApi.getImages(movieId, page)
    }

    suspend fun getSimilarMovies(movieId: Int): ItemsResponse<MovieShortDto> {
        return Network.kinopoiskApi.getSimilarMovies(movieId)
    }

    suspend fun getTopMovies(type: TopMoviesType, page: Int ): TopMoviesResponse {
        return Network.kinopoiskApi.getTopList(type, page)
    }
    suspend fun getMovies(filterParams: MovieFilterParams): ItemsResponse<MovieShortDto> {
        return Network.kinopoiskApi.getMoviesByFilters(
            countries = filterParams.countries,
            genres = filterParams.genres,
            type = filterParams.type,
            ratingFrom = filterParams.ratingFrom,
            ratingTo = filterParams.ratingTo,
            yearFrom = filterParams.yearFrom,
            yearTo = filterParams.yearTo,
            imdbId = filterParams.imdbId,
            keyword = filterParams.keyword,
            page = filterParams.page,
        )
    }

    suspend fun getMovieSeasons(movieId: Int): ItemsResponse<MovieSeriesSeasonDto> {
        return Network.kinopoiskApi.getSeasons(movieId)
    }

    suspend fun getStaffById(staffId: Int): StaffDto {
        return Network.kinopoiskApi.getStaffByID(staffId)
    }
}