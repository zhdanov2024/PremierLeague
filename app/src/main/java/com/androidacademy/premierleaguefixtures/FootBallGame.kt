package com.androidacademy.premierleaguefixtures

import kotlin.random.Random

class FootballGame {
    var team1Goals: Int = 0
        private set
    var team2Goals: Int = 0
        private set

    fun setGoals(team1Goals: Int, team2Goals: Int) {
        if (team1Goals >= 0 && team2Goals >= 0) {
            this.team1Goals = team1Goals
            this.team2Goals = team2Goals
        } else {
            println("Goals cannot be negative")
        }
    }

    fun goalDifference(): Int {
        return kotlin.math.abs(team1Goals - team2Goals)
    }

    override fun toString(): String {
        return "Team 1 Goals: $team1Goals, Team 2 Goals: $team2Goals"
    }
}

fun main() {
    //// Creating and setting random goals for 10 FootballGame objects
    val matches = Array(10) { FootballGame().apply {
        setGoals(Random.nextInt(0, 6), Random.nextInt(0, 6))
    }}

    //// Printing the results of all matches
    println("All Matches:")
    matches.forEachIndexed { index, match -> println("Match ${index + 1}: $match") }

    //// Remove matches that are a draw
    val nonDrawMatches = matches.filter { it.team1Goals != it.team2Goals }

    //// Printing the non-draw matches
    println("\nNon-Draw Matches:")
    nonDrawMatches.forEachIndexed { index, match -> println("Match ${index + 1}: $match") }

    //// Check if there are non-draw matches
    if (nonDrawMatches.isNotEmpty()) {
        // Find the maximum goal difference
        val maxGoalDifference = nonDrawMatches.maxOf { it.goalDifference() }
        println("\nMaximum Goal Difference: $maxGoalDifference")

        //// Create a set of matches with the maximum goal difference
        val maxGoalDifferenceMatches = nonDrawMatches.filter { it.goalDifference() == maxGoalDifference }.toSet()

        //// Printing the results of matches with the maximum goal difference
        println("\nMatches with Maximum Goal Difference:")
        maxGoalDifferenceMatches.forEachIndexed { index, match -> println("Match ${index + 1}: $match") }
    } else {
        println("\nNo non-draw matches found.")
    }
}
