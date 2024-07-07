package com.androidacademy.premierleaguefixtures

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MatchRepository(
    private val matchDetailsDao: MatchDetailsDao,
    private val matchApiService: MatchApiService
) {
    fun getMatches(): Flow<List<MatchDetails>> {
        return matchDetailsDao.getAllMatches().map { entities ->
            entities.map { it.toMatchDetails() }
        }
    }

    suspend fun refreshMatches() {
        val response = matchApiService.getMatches()
        if (response.isSuccessful) {
            response.body()?.let { matches ->
                matchDetailsDao.insertMatches(matches.map { it.toMatchDetailsEntity() })
            }
        }
    }
}

fun MatchDetailsEntity.toMatchDetails(): MatchDetails {
    return MatchDetails(
        matchNumber, roundNumber, dateUtc, location, homeTeam, awayTeam, group, homeTeamScore, awayTeamScore
    )
}

fun MatchDetails.toMatchDetailsEntity(): MatchDetailsEntity {
    return MatchDetailsEntity(
        matchNumber, roundNumber, dateUtc, location, homeTeam, awayTeam, group, homeTeamScore, awayTeamScore
    )
}
