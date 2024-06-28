package com.androidacademy.premierleaguefixtures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MatchViewModel(private val repository: MatchRepository) : ViewModel() {

    private val _matches = MutableStateFlow<Result<List<MatchDetails>>>(Result.Loading)
    val matches: StateFlow<Result<List<MatchDetails>>> = _matches

    init {
        fetchMatches()
    }

    private fun fetchMatches() {
        viewModelScope.launch {
            repository.getMatches().collect {
                _matches.value = it
            }
        }
    }
}
