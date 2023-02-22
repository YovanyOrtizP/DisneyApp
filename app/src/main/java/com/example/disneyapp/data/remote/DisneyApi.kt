package com.example.disneyapp.data.remote

import com.example.disneyapp.data.model.DisneyResponse
import retrofit2.Response
import retrofit2.http.GET

interface DisneyApi {
    //https://api.disneyapi.dev/characters
    @GET(CHARACTERS)
    suspend fun getCharacters(): Response<DisneyResponse>


    companion object{
        const val BASE_URL = "https://api.disneyapi.dev"
        const val CHARACTERS = "/characters"
    }

}