package com.example.LevelUpLife.LevelUp

data class Users(
    val email : String = "",
    val password : String = "",
    val isRegistred : Boolean = false

){
constructor(): this("","",false)
}
