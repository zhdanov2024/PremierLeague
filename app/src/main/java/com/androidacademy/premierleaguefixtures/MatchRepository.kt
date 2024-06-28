package com.androidacademy.premierleaguefixtures

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MatchRepository(private val apiService: MatchApiService) {

    fun getMatches(): Flow<Result<List<MatchDetails>>> = flow {
        emit(Result.Loading)
        try {
            val matches = apiService.getMatches()
            emit(Result.Success(matches))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}