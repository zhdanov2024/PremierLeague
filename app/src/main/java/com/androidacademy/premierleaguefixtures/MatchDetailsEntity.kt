package com.androidacademy.premierleaguefixtures

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match_details")
data class MatchDetailsEntity(
    @PrimaryKey val matchNumber: Int,
    val roundNumber: Int,
    val dateUtc: String,
    val location: String,
    val homeTeam: String,
    val awayTeam: String,
    val group: String?,
    val homeTeamScore: Int?,
    val awayTeamScore: Int?
)