package com.example.LevelUpLife.LevelUp.api

import com.google.gson.annotations.SerializedName

class Advice{
    

/*
    val author: String = ""
    val quote: String = ""

    override fun toString(): String {
        return "Your advice: author='$author' \n quote='$quote'"
    }


 */


 @SerializedName("total_results")
    var amount: String? = ""

    var query: String? = ""

    var slips: ArrayList<Slips> = arrayListOf()

    override fun toString(): String {
        return "Advice: slips=$slips)"
    }


}