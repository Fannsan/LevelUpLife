package com.example.LevelUpLife.LevelUp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GoalViewModel: ViewModel() {

    //Setup Mutblestateflow
    private val _goalUiState = MutableStateFlow(GoalUIState())
    val goalUiState: StateFlow<GoalUIState> = _goalUiState.asStateFlow()


    fun addNewGoal(newGoal:String){
        _goalUiState.update {
            state -> state.copy(
            goalList = arrayListOf(newGoal)
        )
        }
        }
    }



