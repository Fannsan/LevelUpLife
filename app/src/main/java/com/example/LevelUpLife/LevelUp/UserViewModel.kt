package com.example.LevelUpLife.LevelUp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel : ViewModel() {


    private val _uiState = MutableStateFlow(Users())
    val uiState: StateFlow<Users> = _uiState.asStateFlow()

    //method to set the current UiState to the updated values if the user update it
    fun setCurrentUser(username: String, email: String, password: String, goal: String, goalList: ArrayList<Goal>?, id: String){

        //update my uiState in my User
        _uiState.update {
            currentState -> currentState.copy(
            username = username,
            email = email,
            password = password,
            goal = currentState.goal,
            goalList = goalList,
            id = id

        )
        }

    }


}

