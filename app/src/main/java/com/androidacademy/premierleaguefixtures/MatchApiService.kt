package com.androidacademy.premierleaguefixtures

import retrofit2.http.GET

interface MatchApiService {
    @GET("feed/json/epl-2023")
    suspend fun getMatches(): List<MatchDetails>
}