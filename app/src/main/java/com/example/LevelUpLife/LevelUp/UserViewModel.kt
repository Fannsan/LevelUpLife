package com.example.LevelUpLife.LevelUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()


    //method to set the current UiState to my email from the user input
    fun loggedInUser(email: String){

        _uiState.update {
            currentState -> currentState.copy(
            userEmail = email)
        }
        println(email)

    }


   fun getUserEmail():String {
        return uiState.value.userEmail
    }
}

