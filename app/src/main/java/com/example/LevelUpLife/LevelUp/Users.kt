package com.example.LevelUpLife.LevelUp

data class Users(
    val username : String = "",
    val email : String = "",
    val password : String = "",
    val goalList: ArrayList<String> = ArrayList(
        arrayListOf()),

){
constructor(): this("","","",)


}
