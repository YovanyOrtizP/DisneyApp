package com.example.disneyapp.data.repository

import com.example.disneyapp.data.model.DisneyResponse
import retrofit2.Response

interface DisneyRepository {

    suspend fun getCharacters(): Response<DisneyResponse>
}