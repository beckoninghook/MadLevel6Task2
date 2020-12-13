package com.example.madlevel6task2.model

import com.google.gson.annotations.SerializedName

data class Movie (
    @SerializedName("text") var text: String,
    @SerializedName("number") var number: Int,
    @SerializedName("found") var found: Boolean,
    @SerializedName("type") var type: String
)