package com.example.disneyapp.data.remote

import com.example.disneyapp.data.model.DisneyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DisneyApi {
    //https://api.disneyapi.dev/characters
    //https://api.disneyapi.dev/characters?page=2
    @GET(CHARACTERS)
    suspend fun getCharacters(
        @Query(PARAM_PAGE) nextPage: Int? = null
    ): Response<DisneyResponse>

    companion object {
        const val BASE_URL = "https://api.disneyapi.dev"
        const val CHARACTERS = "/characters"
        const val PARAM_PAGE = "page"
    }

}

