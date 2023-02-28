package com.example.disneyapp.util

import com.example.disneyapp.data.model.DisneyResponse

sealed class ResponseType<out T>{
    object LOADING : ResponseType<Nothing>()
    data class SUCCESS<T>(val response: T): ResponseType<T>()
    class ERROR(val e: String): ResponseType<Nothing>()
}
