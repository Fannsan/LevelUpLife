package com.example.LevelUpLife.LevelUp

data class Users(
    val username : String? = "",
    val email : String? = "",
    val password : String? = "",
    val goal : String? = null,
    val goalList: ArrayList<Goal>? = null,
    val id : String? = null


){
    //constructor(): this("","","",)
    override fun toString(): String {
        return "Users(username=$username, email=$email, password=$password, goal=$goal, goalList=$goalList, id=$id)"
    }


}
