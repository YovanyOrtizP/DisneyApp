package com.example.disneyapp.data.repository

import com.example.disneyapp.data.model.DisneyData
import com.example.disneyapp.data.model.DisneyResponse
import com.example.disneyapp.util.ResponseType
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DisneyRepository {
    fun getCharactersFlow(): Flow<ResponseType<List<DisneyData>>>
}
