package com.example.LevelUpLife.LevelUp.api

import com.google.gson.annotations.SerializedName

class ProfilePicture {

    @SerializedName("image") //change name for API-key-value-pair
    val myImage: String = ""

    @SerializedName("link")
    val myLink: String = ""
}