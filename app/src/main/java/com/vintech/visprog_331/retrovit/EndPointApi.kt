package com.vintech.visprog_331.retrovit

import com.vintech.visprog_331.model.NowPlaying
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ):Response<NowPlaying>
}