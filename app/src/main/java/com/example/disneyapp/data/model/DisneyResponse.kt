package com.example.disneyapp.data.model


import com.google.gson.annotations.SerializedName

data class DisneyResponse(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("data")
    val data: List<DisneyData>? = listOf(),
    @SerializedName("nextPage")
    val nextPage: String? = "",
    @SerializedName("totalPages")
    val totalPages: Int? = 0
)