package com.example.LevelUpLife.LevelUp.api

import retrofit2.Call
import retrofit2.http.GET

interface ProfilePictureAPI {
    @GET("/floof/")
    fun getInfo(): Call<ProfilePicture>

}