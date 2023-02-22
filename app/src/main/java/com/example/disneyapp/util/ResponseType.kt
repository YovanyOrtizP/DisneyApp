package com.example.disneyapp.util

import com.example.disneyapp.data.model.DisneyResponse

sealed class ResponseType{
    object LOADING : ResponseType()
    data class SUCCESS(val response: DisneyResponse): ResponseType()
    class ERROR(val e: String): ResponseType()
}
