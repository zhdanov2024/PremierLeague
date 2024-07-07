package com.androidacademy.premierleaguefixtures

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PremierLeagueFixturesApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        val database = AppDatabase.getDatabase(this)
        val repository = MatchRepository(database.matchDetailsDao(), NetworkModule.matchApiService)

        applicationScope.launch {
            repository.refreshMatches()
        }
    }
}