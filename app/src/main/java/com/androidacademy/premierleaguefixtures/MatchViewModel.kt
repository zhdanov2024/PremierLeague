package com.androidacademy.premierleaguefixtures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MatchViewModel(private val repository: MatchRepository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val matches: Flow<Result<List<MatchDetails>>> = _searchQuery
        .flatMapLatest { query ->
            repository.getMatches()
                .map { matches ->
                    val filteredMatches = if (query.isEmpty()) {
                        matches
                    } else {
                        matches.filter {
                            it.homeTeam.contains(query, ignoreCase = true) || it.awayTeam.contains(query, ignoreCase = true)
                        }
                    }
                    Result.Success(filteredMatches) as Result<List<MatchDetails>>
                }
                .catch { e -> emit(Result.Error(e)) }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, Result.Loading)

    fun searchMatches(query: String) {
        viewModelScope.launch {
            _searchQuery.emit(query)
        }
    }

    init {
        viewModelScope.launch {
            repository.refreshMatches()
        }
    }
}