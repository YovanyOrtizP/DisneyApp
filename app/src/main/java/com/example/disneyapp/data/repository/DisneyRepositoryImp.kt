package com.example.disneyapp.data.repository

import com.example.disneyapp.data.model.DisneyResponse
import com.example.disneyapp.data.remote.DisneyApi
import retrofit2.Response
import javax.inject.Inject

class DisneyRepositoryImp @Inject constructor(
    private val disneyApi: DisneyApi
): DisneyRepository{
    override suspend fun getCharacters(): Response<DisneyResponse> {
        return disneyApi.getCharacters()
    }
}