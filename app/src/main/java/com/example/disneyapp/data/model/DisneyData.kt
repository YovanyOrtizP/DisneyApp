package com.example.disneyapp.data.model


import com.google.gson.annotations.SerializedName

data class DisneyData(
    @SerializedName("allies")
    val allies: List<Any?>? = listOf(),
    @SerializedName("enemies")
    val enemies: List<Any?>? = listOf(),
    @SerializedName("films")
    val films: List<String?>? = listOf(),
    @SerializedName("_id")
    val id: Int? = 0,
    @SerializedName("imageUrl")
    val imageUrl: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("parkAttractions")
    val parkAttractions: List<String?>? = listOf(),
    @SerializedName("shortFilms")
    val shortFilms: List<String?>? = listOf(),
    @SerializedName("tvShows")
    val tvShows: List<String?>? = listOf(),
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("videoGames")
    val videoGames: List<String?>? = listOf()
)