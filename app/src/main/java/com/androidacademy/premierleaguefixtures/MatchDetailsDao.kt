package com.androidacademy.premierleaguefixtures

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDetailsDao {
    @Query("SELECT * FROM match_details")
    fun getAllMatches(): Flow<List<MatchDetailsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatches(matches: List<MatchDetailsEntity>)
}