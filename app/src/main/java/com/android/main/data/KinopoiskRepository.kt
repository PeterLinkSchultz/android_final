package com.example.main.data

import com.android.main.data.ItemsResponse
import com.android.main.data.image.GalleryImageDto
import com.android.main.data.movie.MovieShortDto
import com.android.main.data.movie.MovieDto
import com.android.main.data.movie.MovieListResponse
import com.android.main.data.staff.StaffDto
import javax.inject.Inject

class KinopoiskRepository @Inject constructor(){
    suspend fun getPremieres(): ItemsResponse<MovieShortDto> {
        return Network.kinopoiskApi.getPremieres()
    }
    suspend fun getMovie(movieId: Int): MovieDto {
        return Network.kinopoiskApi.getMovieById(movieId)
    }
    suspend fun getStaffByMovieId(movieId: Int): List<StaffDto> {
        return Network.kinopoiskApi.getStaffByMovieID(movieId)
    }

    suspend fun getMovieImages(movieId: Int, page: Int): ItemsResponse<GalleryImageDto> {
        return Network.kinopoiskApi.getImages(movieId, page)
    }

    suspend fun getSimilarMovies(movieId: Int): ItemsResponse<MovieShortDto> {
        return Network.kinopoiskApi.getSimilarMovies(movieId)
    }
}