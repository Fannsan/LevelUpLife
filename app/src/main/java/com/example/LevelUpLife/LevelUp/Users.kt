package com.example.LevelUpLife.LevelUp

data class Users(
    val username : String = "",
    val email : String = "",
    val password : String = "",
    val isRegistred : Boolean = false

){
constructor(): this("","","",false)
}
