package com.example.lab2.LevelUp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LevelUpViewModel: ViewModel() {

    //Setup mutableStateFlow
   private val _uiState = MutableStateFlow(LevelUpUiState())
    val uiState: StateFlow<LevelUpUiState> = _uiState.asStateFlow()



    fun getRandomExcitedQuote():String{

        return uiState.value.excitedList.random()
    }

    fun getRandomTiredQuote(): String {
        return uiState.value.tiredList.random()
    }

    fun getRandomAnxiousQuote(): String{
        return uiState.value.tiredList.random()
    }

    fun getRandomIrritatedQuote(): String {

        return uiState.value.irritatedList.random()
    }



}