package com.example.disneyapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.disneyapp.data.model.DisneyData
import com.example.disneyapp.data.model.DisneyResponse
import com.example.disneyapp.data.remote.DisneyApi

import com.example.disneyapp.ui.characters.paging.DisneyPaging
import com.example.disneyapp.util.ResponseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class DisneyRepositoryImp @Inject constructor(
    private val disneyApi: DisneyApi
): DisneyRepository{
    override fun getCharactersFlow(): Flow<ResponseType<List<DisneyData>>> = flow {
    emit(ResponseType.LOADING)

        try {
            val response = disneyApi.getCharacters()
            if (response.isSuccessful){
                response.body()?.let {
                    it.data?.let { data ->
                        emit(ResponseType.SUCCESS(data))
                    }
                }
            }else{
                emit(ResponseType.ERROR(response.message()))
            }
        } catch (e:java.lang.Exception) {
            emit(ResponseType.ERROR(e.localizedMessage))
        }
    }
//    fun getPages() = Pager(
//        config = PagingConfig(pageSize = 50, maxSize = 100),
//        pagingSourceFactory = {DisneyPaging(disneyApi)}
//    ).liveData
}
//We don't expose the implementation