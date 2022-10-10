package com.vintech.visprog_331.repository

import com.vintech.visprog_331.retrovit.EndPointApi
import javax.inject.Inject

class NowPlayingRepository @Inject constructor(private val api: EndPointApi){
    suspend fun getNowPlayingData(
        apiKey: String,
        language: String,
        page: Int) =
        api.getNowPlaying(apiKey, language, page)
}