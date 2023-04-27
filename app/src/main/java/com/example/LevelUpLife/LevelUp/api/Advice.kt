package com.example.LevelUpLife.LevelUp.api

import com.google.gson.annotations.SerializedName

class Advice{


 @SerializedName("total_results")
    var amount: String? = ""

    var query: String? = ""

    var slips: ArrayList<Slips> = arrayListOf()

    override fun toString(): String {
        return "Advice: slips=$slips)"
    }


}