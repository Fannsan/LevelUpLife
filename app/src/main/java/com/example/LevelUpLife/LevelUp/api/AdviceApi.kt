package com.example.LevelUpLife.LevelUp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AdviceApi {
    //endpoint
    @GET("/advice/search/{query}")
    fun getAdvice(@Path("query") query : String) : Call<Advice>

}