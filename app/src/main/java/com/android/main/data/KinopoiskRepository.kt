package com.example.main.data

import com.android.main.data.movie.MovieDto
import com.android.main.data.movie.MovieListResponse
import com.android.main.data.staff.StaffDto
import com.android.main.entity.Movie
import javax.inject.Inject

class KinopoiskRepository @Inject constructor(){
    suspend fun getPremieres(): MovieListResponse {
        return Network.kinopoiskApi.getPremieres()
    }
    suspend fun getMovie(movieId: Int): MovieDto {
        return Network.kinopoiskApi.getMovieById(movieId)
    }
    suspend fun getStaffByMovieId(movieId: Int): List<StaffDto> {
        return Network.kinopoiskApi.getStaffByMovieID(movieId)
    }
}